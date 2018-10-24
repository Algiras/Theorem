name := "Theorem"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.3.4" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.26",
  "org.scalaz" %% "scalaz-effect" % "7.2.26"
)


scalacOptions in Test ++= Seq("-Yrangepos")