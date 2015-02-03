# Java Message Service

Project demonstrate messaging mechanisms  using RabbirMQ. AMQP is acually used to send messages to and from a brocker.
Basic introduction to AMQP could be found [here](https://spring.io/understanding/AMQP).
Great [tutorials](http://www.rabbitmq.com/getstarted.html) are available on RabbitMQ official web site.

# Usage

First of all you need to install [RabbitMQ server](http://www.rabbitmq.com/install-windows-manual.html).
When you have your brocker up and running package source code into jar file using `gradle` from jms project root folder

```gradle
gradle fatJar
```

It will produce jar file that can be found in `jms/build/libs/`.

## Run 'Hello, World'

Run reciever 
```
java -cp jms-1.2.1.jar com.epam.rabbitmq.helloworld.Receive
```

Run sender 
```
java -cp jms-1.2.1.jar com.epam.rabbitmq.helloworld.Send
```

## Run Working Queue example

Run reciever multiple times to see round-robin algorithm in action 
```
java -cp jms-1.2.1.jar com.epam.rabbitmq.workqueue.Worker
```

Run sender
```
java -cp jms-1.2.1.jar com.epam.rabbitmq.helloworld.NewTask
```

Using sender console send custom messages to the workers. Dots in the end of the message mean pause.

## Code Quality Check

Checkstyle and FindBugs are triggered when `gradle build` is launched. All checks could be run as `gradle check`.

To run them separately see the list below.

* Checkstyle
  * `gradle checkstyleMain` - to check style of application sources
  * `gradle checkstyleTest` - to check style of tests sources
* FindBugs
  * `gradle findbugsMain` - find bugs in application sources
  * `gradle findbugsTest` - find bugs in tests sources
* JoCoCo - generate test coverage reports
  * `gradle jococoTestReport` - generate HTML file with test coverage results