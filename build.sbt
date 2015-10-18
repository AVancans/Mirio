name := "Mirio"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies +=  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"

libraryDependencies +=  "org.mongodb" %% "casbah" % "2.8.2"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
//
//libraryDependencies +=  "org.slf4j" %% "slf4j-api" % "1.7.5"

//libraryDependencies +=  "org.slf4j" %% "slf4j-log4j12" % "1.7.5"
//

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "org.slf4j" % "slf4j-log4j12" % "1.6.4"
)
