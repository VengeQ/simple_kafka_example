ThisBuild / organization := "com.example"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.8"

name := "KafkaExamples"

version := "0.1"

val kafka = "org.apache.kafka" %% "kafka" % "2.2.1"
val log4j = "org.apache.logging.log4j" % "log4j-api" % "2.11.2"
val log4jCore = "org.apache.logging.log4j" % "log4j-core" % "2.11.2"
val sl4jImpl = "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.11.2"
val json = "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.9"

lazy val producer = (project in file("producer"))
  .settings(
    libraryDependencies ++= Seq(kafka, log4j, log4jCore, sl4jImpl),
    mainClass in assembly := Some("app.ProducerApp")
  )

lazy val consumer = (project in file("consumer"))
  .settings(
    libraryDependencies ++= Seq(kafka, log4j, log4jCore, sl4jImpl),
    mainClass in assembly := Some("app.ConsumerApp")
  )

lazy val root = (project in file("."))
  .aggregate(producer, consumer)


fork in run := true
connectInput in run := true
fork in test := true
connectInput in test := true