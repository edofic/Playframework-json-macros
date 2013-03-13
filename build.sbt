scalaVersion := "2.10.0"

organization := "com.edofic"

name := "play22-json-backport"

version := "0.1-SNAPSHOT"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies += "play" %% "play" % "2.1.0"

publishTo := Some(Resolver.file("snapshots", new File(Path.userHome, "/git/repository/snapshots")))
