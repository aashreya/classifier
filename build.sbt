name := "Classification"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.lucene" % "lucene-analyzers-common" % "7.6.0")