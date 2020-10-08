package fun.data

import io.circe.generic.auto._
import io.circe.parser._

import scala.io.Source
import java.time.ZonedDateTime

object Main {
  def main(args: Array[String]) = {
    val data = readData()

    // Calculate the average number of available Velib’ per station and per day’s hour to know what is the best time to take a bike and in which station.
    val freeBikesByStationAndHour = groupedByStationAndHour(data, fields => fields.nbbike + fields.nbebike)

    // Calculate the average number of available docks per station and per day’s hour to know what is the best time to put a bike and in which station.
    val freeDocksByStationAndHour = groupedByStationAndHour(data, fields => fields.nbfreedock + fields.nbfreeedock)

    // Calculate the Velib’ stations top 3 with the more available bikes per day
    val topThreeStationsWithBikes = freeBikesByStationAndHour
      .map { case (stationId, counts) => stationId -> counts.map(_._2).sum / counts.size }
      .toList
      .sortBy(_._2)
      .takeRight(3)

    println("===")
    println("Available bikes per hour")
    println(prettyMap(freeBikesByStationAndHour))
    println("===")
    println("Available docks per hour")
    println(prettyMap(freeDocksByStationAndHour))
    println("===")
    println("Top 3 stations with available bikes")
    println(prettyList(topThreeStationsWithBikes))

    ()
  }

  def prettyMap(data: Map[StationId, Map[Int, Int]]): String =
    data
      .map { case (stationId, data) =>
        s"${stationId.name}:\n\t${data.toList.sortBy(_._1).map { case (hour, count) => s"${hour}h: $count"}.mkString("\n\t")}"
      }
      .mkString("\n")

  def prettyList(data: List[(StationId, Int)]): String =
    data
      .map { case (stationId, count) => s"${stationId.name}: $count" }
      .mkString("\n")

  def groupedByStationAndHour(data: Iterable[VelibData], number: Fields => Int): Map[StationId, Map[Int, Int]] =
    data
      .flatMap(_.records)
      .groupBy(record => StationId(code = record.fields.station_code, name = record.fields.station_name))
      .map { case (stationId, records) =>
        val byHour = records
          .groupBy(record => ZonedDateTime.parse(record.record_timestamp).toLocalDateTime.getHour)
          .map { case (hour, records) =>
            val average = records.map(record => number(record.fields)).sum / records.size
            hour -> average
          }.toMap

        stationId -> byHour
      }

  def readData(): Iterable[VelibData] = {
    val bufferedSource = Source.fromFile("velib_dataset_c271c5d8-6b77-4557-845c-3b449863bbb0.txt")
    val data = bufferedSource.getLines.flatMap(line => decode[VelibData](line).toOption).to(Iterable)
    bufferedSource.close()
    data
  }
}

case class StationId(code: String, name: String)
