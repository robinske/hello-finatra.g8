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
