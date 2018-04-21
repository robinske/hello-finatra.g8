# Hello Finatra

A [Giter8][g8] template for a Hello World [Finatra](https://github.com/twitter/finatra) application.

## Using this template

### Install

Requirements:

* [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [SBT](https://www.scala-sbt.org/download.html), I’m using version 0.13.6

In a terminal window type:

```
sbt new robinske/hello-finatra.g8
```

You can name your project anything you like, but avoid using numbers or dashes.

![image](https://user-images.githubusercontent.com/3673341/39088781-36029586-456d-11e8-9940-28fa04a7bfed.png)

### Running 'Hello World'

Navigate into your directory, in my case:

```
cd finatra_demo
```

Then run using SBT:

```
sbt run
```

Finatra will start on [port 8888](http://localhost:8888/hello?name=Kelley):

![image](https://user-images.githubusercontent.com/3673341/39088806-9a32425e-456d-11e8-92c0-54742486282f.png)

Hooray!

## Incorporating Twilio

We can use our Finatra API to respond to incoming text messages using [Twilio](http://twilio.com/try-twilio). Twilio provides APIs for communications, including [SMS](https://www.twilio.com/sms), [Voice](https://www.twilio.com/voice), and [Authentication](https://www.twilio.com/authy).

In this Quickstart, you will learn how to:

1. Sign up for Twilio and get your first SMS-enabled Twilio phone number
1. Set up your development environment to send and receive messages
1. Receive inbound text messages
1. Reply to incoming messages with an SMS

### Sign up for Twilio and get a phone number
If you already have a Twilio account and an SMS-enabled Twilio phone number, you’re all set here! [Feel free to jump to the next step.](https://github.com/robinske/hello-finatra.g8/blob/master/README.markdown#allow-twilio-to-talk-to-your-flask-application)

Before you can send an SMS from Python, you'll need to [sign up for a Twilio account](https://www.twilio.com/try-twilio) or sign into your existing account and [purchase an SMS-capable phone number](https://www.twilio.com/console/phone-numbers).

If you don't currently own a Twilio phone number with SMS functionality, you'll need to purchase one.  After navigating to the [Buy a Number](https://www.twilio.com/console/phone-numbers/search) page, check the "SMS" box and click "Search."

![image](https://s3.amazonaws.com/com.twilio.prod.twilio-docs/images/buy_a_number.width-800.jpg)

You’ll then see a list of available phone numbers and their capabilities. Find a number that suits your fancy and click "Buy" to add it to your account.

![image](https://s3.amazonaws.com/com.twilio.prod.twilio-docs/images/sms_buy_number.width-800.png)

Now that you have a Twilio account and a programmable phone number, you can start writing some code! To make things even easier, we'll next install Twilio's official helper for Python applications.

### Allow Twilio to talk to your application

We’re about to build a small Finatra application to receive incoming messages. Before we do that, we need to make sure that Twilio can reach your application.

Most Twilio services use [webhooks](https://www.twilio.com/docs/glossary/what-is-a-webhook) to communicate with your application. When Twilio receives an SMS, for example, it reaches out to a URL in your application for instructions on how to handle the message.

When you’re working on your Finatra application in your development environment, your app is only reachable by other programs on your computer, so Twilio won’t be able to talk to it. We need to solve this problem by making your application accessible over the internet.

While there are a lot of ways to do this, like deploying your application to Heroku or AWS, you'll probably want a less laborious way to test your Twilio application. For a lightweight way to make your app available on the internet, we recommend a tool called Ngrok. Once started, Ngrok provides a unique URL on the ngrok.io domain which forwards incoming requests to your local development environment.

![image](https://s3.amazonaws.com/com.twilio.prod.twilio-docs/images/webhook_ngrok_flow.width-800.png)

If you don’t already use Ngrok, head over to their [download page](https://ngrok.com/download) and grab the appropriate binary for your operating system. Once downloaded, unzip the package.

If you're working on a Mac or Linux, you're all set. If you're on Windows, follow our guide on [how to install and configure ngrok on Windows](https://www.twilio.com/docs/usage/tutorials/how-use-ngrok-windows-and-visual-studio-test-webhooks). For more info on ngrok, including some great tips and tricks, check out [this in-depth blog post](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html).

Once downloaded, start that Hello World application we made previously:

```
sbt run
```

_Your local application *must* be running locally for Ngrok to do its magic._

Then open a new terminal tab or window and start Ngrok with this command:

```
./ngrok http 8888
```

You should see output similar to this:

![image](https://user-images.githubusercontent.com/3673341/39088980-56b9e99c-4571-11e8-89cc-766ca8c45e3b.png)

Copy your public URL from this output and paste it into your browser. You should see your Finatra application's "Hello, World!" message.

### Receive and reply to inbound SMS messages with Finatra

When someone sends an SMS to your Twilio phone number, Twilio makes an HTTP request to your server asking for instructions on what to do next. Once you receive the request, you can tell Twilio to reply with an SMS, kick off a phone call, store details about the SMS in your database, or trigger something else entirely - it’s all up to you!

For this Quickstart, we’ll have our Finatra app reply to incoming SMS messages with a thank you to the sender. Open up `Server.scala` again and update the code in the `HelloController` class to look like this code sample:

```
class HelloController extends Controller {

  post("/sms") { request: Request =>
    val twiml =
      <Response>
        <Message>
          Hi thanks for coming to Scalabridge - krobinson@twilio.com
        </Message>
      </Response>

    response.ok
      .contentType("text/xml")
      .body(twiml)
  }

}
```
_When your phone number receives an incoming message, Twilio will send an HTTP request to your server. This code shows how your server should respond to reply with a text message._

Save this file and restart your app with 

```
sbt run
```
Double-check that Ngrok is still running on your localhost port. Now Twilio will be able to find your application - but first, we need to tell Twilio where to look.

#### Configure your webhook URL

For Twilio to know where to look, you need to configure your Twilio phone number to call your webhook URL whenever a new message comes in.

1. Log into Twilio.com and go to the [Console's Numbers page](https://www.twilio.com/console/phone-numbers/incoming).
1. Click on your SMS-enabled phone number.
1. Find the Messaging section. The default “CONFIGURE WITH” is what you’ll need: "Webhooks/TwiML".
1. In the “A MESSAGE COMES IN” section, select "Webhook" and paste in the URL you want to use.

![image](https://s3.amazonaws.com/com.twilio.prod.twilio-docs/images/SMS_Webhook.width-800.png)

Save your changes - you're ready!

#### Test your application
As long as your localhost and the Ngrok servers are up and running, we’re ready for the fun part - testing our new Finatra application!

Send a text message from your mobile phone to your Twilio phone number. You should see an HTTP request in your Ngrok console. Your Finatra app will process the text message, and you’ll get your response back as an SMS.

### Where to next?

Now that you know the basics of sending and receiving SMS and MMS text messages with Python, you might want to check out these resources.

* [Send an SMS message from your application](https://www.twilio.com/blog/2017/11/getting-started-with-scala-and-twilio.html)
* Dive into the [API Reference documentation for Twilio SMS](https://www.twilio.com/docs/sms/api)
* Learn how to build cool things with [TwilioQuest](https://www.twilio.com/quest/welcome), our interactive, self-paced game that teaches you how to Twilio.


## Credits

Forked from the Scala Center Hello World Giter8 template.

Template license
----------------
Written in 2017 by the Scala Center
[other author/contributor lines as appropriate]

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
