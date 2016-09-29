name := "meetup"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.4.6"

  Seq(
    "org.scala-lang" % "scala-reflect" % "2.11.8",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  )
}

