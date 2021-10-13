name := "scala-underneath"
version := "1.0.0-SNAPSHOT"

scalaVersion := "2.13.6"
// scala compiler options
//   https://docs.scala-lang.org/overviews/compiler-options/index.html
scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-unchecked",
  "-deprecation",
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.10" % Test,
  "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,
  "org.scalamock" %% "scalamock" % "5.1.0" % Test,
  "junit" % "junit" % "4.13.2" % Test,
)
