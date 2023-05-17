name := """ad-consumer"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// --- Webservice ---
libraryDependencies += ws
libraryDependencies += ehcache
//libraryDependencies += javaWs
//libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.1.10"
//libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json" % playWsStandaloneVersion


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
