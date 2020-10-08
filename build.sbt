import Options._
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

inThisBuild(Seq(
  version := "0.1.0-SNAPSHOT",

  scalaVersion := "2.13.1"
))

lazy val commonSettings = Seq(
  addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full),

  resolvers ++=
    ("jitpack" at "https://jitpack.io") ::
    Nil,

  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.2.0" % Test,
  ),

  scalacOptions ++= CrossVersion.partialVersion(scalaVersion.value).map(v =>
    allOptionsForVersion(s"${v._1}.${v._2}")
  ).getOrElse(Nil),
  scalacOptions in (Compile, console) ~= (_.diff(badConsoleFlags))
)

lazy val jsSettings = Seq(
  useYarn := true,

  requireJsDomEnv in Test := true,
  scalaJSUseMainModuleInitializer := true,
  scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
)

// when running the "dev" alias, after every fastOptJS compile all artifacts are copied into
// a folder which is served and watched by the webpack devserver.
// this is a workaround for: https://github.com/scalacenter/scalajs-bundler/issues/180
lazy val copyFastOptJS = TaskKey[Unit]("copyFastOptJS", "Copy javascript files to target directory")
lazy val webSettings = Seq(
  version in webpack := "4.43.0",
  version in startWebpackDevServer := "3.11.0",
  webpackDevServerExtraArgs := Seq("--progress", "--color"),
  webpackDevServerPort := 12345,
  webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack.config.dev.js"),

  webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly(),

  copyFastOptJS := {
    val inDir = (crossTarget in (Compile, fastOptJS)).value
    val outDir = inDir / "dev"
    val files = Seq(name.value.toLowerCase + "-fastopt-loader.js", name.value.toLowerCase + "-fastopt-library.js", name.value.toLowerCase + "-fastopt.js") map { p => (inDir / p, outDir / p) }
    IO.copy(files, overwrite = true, preserveLastModified = true, preserveExecutable = true)
  }
)

lazy val app = project
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .in(file("app"))
  .settings(commonSettings, jsSettings, webSettings)
  .settings(
    libraryDependencies ++= Seq(
      Deps.outwatch.value,
    )
  )

lazy val data = project
  .in(file("data"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      Deps.circe.core.value,
      Deps.circe.generic.value,
      Deps.circe.parser.value,
    )
  )

lazy val root = project
  .in(file("."))
  .settings(
    skip in publish := true,
  )
  .aggregate(app, data)


// dev command with hot reload
addCommandAlias("dev", "devInit; devWatch; devDestroy")
addCommandAlias("devInit", "app/fastOptJS::webpack; app/fastOptJS::startWebpackDevServer; app/copyFastOptJS;")
addCommandAlias("devWatch", "~; app/fastOptJS; app/copyFastOptJS;")
addCommandAlias("devDestroy", "app/fastOptJS::stopWebpackDevServer;")