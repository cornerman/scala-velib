package fun.app

import cats.effect.{IO, ContextShift}

import org.scalajs.dom
import org.scalajs.dom.experimental.Fetch
import scala.scalajs.js
import scala.scalajs.js.|

object Api {
  private val baseUrl = dom.window.location.href

  def retry[A](ioa: IO[A], maxRetries: Int): IO[A] =
    ioa.handleErrorWith { error =>
      if (maxRetries > 0) retry(ioa, maxRetries - 1)
      else IO.raiseError(error)
    }

  def stations(implicit cs: ContextShift[IO]): IO[List[VelibStation]] =
    get[VelibStationResponse[VelibStation]](s"${baseUrl}velib/opendata/Velib_Metropole/station_information.json").map(_.data.stations.toList)

  def stationStatuses(implicit cs: ContextShift[IO]): IO[List[VelibStationStatus]] =
    get[VelibStationResponse[VelibStationStatus]](s"${baseUrl}velib/opendata/Velib_Metropole/station_status.json").map(_.data.stations.toList)

  private def get[T](url: String)(implicit cs: ContextShift[IO]): IO[T] = IO.fromFuture(IO(
    Fetch.fetch(url).`then`(o => o.json: js.Any | js.Thenable[js.Any]).toFuture
  )).asInstanceOf[IO[T]]
}

@js.native
trait VelibStationResponse[T] extends js.Any {
  def data: VelibStationResponseData[T] = js.native
  def lastUpdatedOther: Double = js.native
  def ttl: Int = js.native
}

@js.native
trait VelibStationResponseData[T] extends js.Any {
  def stations: js.Array[T]
}

@js.native
trait VelibStation extends js.Any {
  def capacity: Int = js.native
  def lat: Double = js.native
  def lon: Double = js.native
  def name: String = js.native
  def station_id: Double = js.native
}

@js.native
trait VelibStationStatus extends js.Any {
  def station_id: Double = js.native
  def is_installed: Int = js.native
  def is_renting: Int = js.native
  def is_returning: Int = js.native
  def last_reported : Int = js.native
  def numBikesAvailable: Int = js.native
  def numDocksAvailable: Int = js.native
  def num_bikes_available: Int = js.native
  def num_bikes_available_types: js.Array[VelibBikes] = js.native
}

@js.native
trait VelibBikes extends js.Any {
  def ebike: js.UndefOr[Int] = js.native
  def mechanical: js.UndefOr[Int] = js.native
}
