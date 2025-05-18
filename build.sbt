scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "tictactoeAI",
    version := "0.1.0",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-Wunused:imports",
      "-Xfatal-warnings"
    )
  )
