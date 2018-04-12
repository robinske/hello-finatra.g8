scalaVersion := "2.12.4"

name := "hello-finatra"

resolvers += Resolver.sonatypeRepo("releases")

initialCommands in console := """
| import com.twitter.util.{Future, FuturePool, Await}
| import com.twilio.Twilio
| import com.twilio.`type`.PhoneNumber
| import com.twilio.rest.api.v2010.account.Message
|
|""".stripMargin


libraryDependencies ++= {
  Seq(
    "com.twitter"   %% "finatra-http"    % "18.4.0",
    "com.twilio.sdk" % "twilio"          % "7.15.5",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
