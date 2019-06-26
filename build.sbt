name := "scala-underneath"
version := "1.0.0-SNAPSHOT"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "org.scalamock" %% "scalamock" % "4.3.0" % Test,
  "junit" % "junit" % "4.12" % Test,
)
