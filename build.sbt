name := "java_admin_test"
version := "1.0"
scalaVersion := "2.11.5"


libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "requirejs" % "2.1.18"
)

libraryDependencies ++= Seq( javaJdbc , cache , javaWs )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

lazy val `java_admin_test` = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb)