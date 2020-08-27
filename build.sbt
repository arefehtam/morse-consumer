name := "morse-consumer"
version := "0.0.1"
scalaVersion := "2.12.10"

val akkaVersion = new {
  val core = "2.5.31"
}

val json4sVersion = "3.6.5"
val sttpVersion = "2.0.9"

libraryDependencies ++= Seq(
  //Config
  "com.typesafe" % "config" % "1.3.2",
  // JSON
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  // Logging
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.26",
  //Queue
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "2.0.1",
  "com.typesafe.akka" %% "akka-stream" % akkaVersion.core,
  "com.rabbitmq" % "amqp-client" % "5.3.0"
)

libraryDependencies ~= {
  _.map(_.exclude("org.slf4j", "slf4j-simple"))
}
