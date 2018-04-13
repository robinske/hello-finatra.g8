scalaVersion := "2.12.4"

name := "hello-finatra"

resolvers += Resolver.sonatypeRepo("releases")

logLevel := Level.Warn

initialCommands in console := """
      |import scala.collection.JavaConverters._
      |import java.net.URI
      |
      |import com.twilio.Twilio
      |import com.twilio.`type`.PhoneNumber
      |import com.twilio.rest.api.v2010.account.Call
      |import com.twilio.rest.api.v2010.account.Message
      |
    """.stripMargin

libraryDependencies ++= {
  Seq(
    "com.twitter"   %% "finatra-http"    % "18.4.0",
    "com.twilio.sdk" % "twilio"          % "7.17.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}

dependencyOverrides ++= Set(
  "com.google.guava"         % "guava"  % "19.0",
  "com.google.code.findbugs" % "jsr305" % "3.0.1"
)

scalacOptions += "-deprecation"
