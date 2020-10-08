import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

object Deps {
  import Def.{ setting => dep }

  // testing
  val scalatest = dep("org.scalatest" %%% "scalatest" % "3.2.0")

  // serialization
  val circe = new {
    private val version = "0.13.0"
    val core = dep("io.circe" %%% "circe-core" % version)
    val generic = dep("io.circe" %%% "circe-generic" % version)
    val parser = dep("io.circe" %%% "circe-parser" % version)
  }

  // web app
  val outwatch = dep("com.github.outwatch.outwatch" %%% "outwatch" % "53a890b")
}
