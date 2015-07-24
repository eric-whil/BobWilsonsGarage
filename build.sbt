import sbt._
import Keys._

organization := "com.bobwilsonsgarage"

name := "akka-service-registry-example"

version := "1.0"

scalaVersion := "2.11.6"

lazy val common = Project(id = "common",
                            base = file("common"))

//lazy val frontEnd = Project(id = "frontEnd",
//                            base = file("frontEnd"))
//                            .dependsOn(common)

lazy val staffing = Project(id = "staffing",
                            base = file("staffing"))
                            .dependsOn(common)

lazy val carRepair = Project(id = "carRepair",
                            base = file("carRepair"))
                            .dependsOn(common)

lazy val detailing = Project(id = "detailing",
                            base = file("detailing"))
                            .dependsOn(common)

lazy val root = Project(id = "root",
                            base = file("."))
                            .aggregate(common, staffing, detailing, carRepair)
//                            .aggregate(common, frontEnd, staffing, carRepair, detailing)