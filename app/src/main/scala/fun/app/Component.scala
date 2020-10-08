package fun.app

import colibri._
import outwatch._
import outwatch.dsl._

import cats.effect.IO
import scala.concurrent.ExecutionContext

import scala.scalajs.js

object Component {
  implicit val contextShift = IO.contextShift(ExecutionContext.global)

  val numberStations = Subject.behavior(3)
  val refreshTrigger = Subject.publish[Unit]
  val refreshInterval = Observable.merge(refreshTrigger, Observable.intervalMillis(60 * 1000))

  val stations: Observable[List[VelibStation]] = Observable.fromAsync(Api.retry(Api.stations, 5))
  val stationStatuses: Observable[List[VelibStationStatus]] = refreshInterval.switchMap(_ => Observable.fromAsync(Api.stationStatuses))

  val sortedStations = stations.map(_.sortBy(station =>
    Distance.meter(lat1 = Constants.addressLat, lon1 = Constants.addressLon)(lat2 = station.lat, lon2 = station.lon)
  )(Ordering.Double.TotalOrdering))

  val closestStations = sortedStations.combineLatestMap(numberStations)(_ take _)
  val stationStatusMap = stationStatuses.map(_.groupBy(_.station_id))

  val editNumberStations = div(
    display.flex,
    flexDirection.row,

    div("Show closest stations:"),

    input(
      marginLeft := "5px",
      width := "80px",
      value <-- numberStations.map(_.toString),
      onChange.valueAsNumber.map(_.toInt) --> numberStations,
      tpe := "number"
    )
  )

  val refreshButton = div(
    span("Automatically refreshes every minute"),

    button(
      marginLeft := "5px",
      "Refresh",
      onClick.use(()) --> refreshTrigger,
    ),
  )

  val spinner = div(
    cls := "sk-chase",
    List.fill(6)(div(cls := "sk-chase-dot"))
  )

  def renderStation(station: VelibStation) = VDomModifier(
    b(s"${station.name}"),
    span(marginLeft := "5px", s"(${station.lat}, ${station.lon})"),
    a(marginLeft := "5px", href := s"https://www.google.com/maps/place/${station.lat},${station.lon}/@${station.lat},${station.lon},15z", "Show on Google Maps"),
  )

  def renderStationStatus(status: VelibStationStatus) = VDomModifier(
    div(s"Is Renting: ${status.is_renting == 1}"),
    div(s"Reported at: ${new js.Date(status.last_reported * 1000.0)}"),
    div(s"E-Bikes: ${status.num_bikes_available_types.map(_.ebike.getOrElse(0)).sum}"),
    div(s"Mechanical Bikes: ${status.num_bikes_available_types.map(_.mechanical.getOrElse(0)).sum}")
  )

  val root = div(
    h3(s"Showing Velib Stations near '${Constants.address}' (${Constants.addressLat}, ${Constants.addressLon})"),

    div(
      marginTop := "10px",
      display.flex,
      flexDirection.row,
      justifyContent.spaceBetween,

      editNumberStations,
      refreshButton
    ),

    div(
      marginTop := "20px",
      display.flex,
      flexDirection.column,
      alignItems.center,

      closestStations.combineLatestMap(stationStatusMap) { (stations, statusMap) =>
        div(
          stations.map { station =>
            val status = statusMap.get(station.station_id).flatMap(_.headOption)

            div(
              renderStation(station),
              status.fold(div("Availability unknown")) { status =>
                div(
                  marginLeft := "10px",
                  renderStationStatus(status)
                )
              }
            )
          }
        )
      }.prepend(spinner)
    )
  )
}
