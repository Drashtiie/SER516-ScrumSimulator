## Dr. Gary
The ScrumBoardSimulator is a 7 year-old JavaFX project that runs a cheesy Scrum Simulation in Java.
The build process doesn't work entirely cleanly as it relies on Java 8, primarily because it uses JavaFX.
If you use a later version of Java you need to get JavaFX separately and update Maven. I found it faster just
to use a Java8 version I had, as JavaFX is in there and the buidl works cleanly. Even when running I notice
a bug or two, but the main thing is this simulation really hardcodes the initial data and just randomizes
tile names. I think there are multiple upgrades that are easily possible.

Because it is tricky to build, I imported with my already built files under folder "target". You can 
directly run the jarfile in there using java-jar.

The original project is at https://github.com/betrcode/ScrumBoardSimulator


# Scrum Board Simulator
or: Scrum Game. It is a simulation of a Scrum Board where the idea is that you can play with different variables such as
team, team velocity, sprint length, stories and story sizes, work-in-progress limits and random disasters such as
unplanned work and see how it affects the Sprint and how much waste is created.

The application is built using JavaFX.

## Prerequisites for development
* Java 8
* Maven 3

## How to build & run tests
* ```mvn clean install```

## How to run
* Launching jar from command line: ```java -jar target/ScrumBoardSimulator-1.0-SNAPSHOT.jar```

## Continuous Integration
[http://jenkins2.crisp.se/job/betrcode/job/ScrumBoardSimulator/](http://jenkins2.crisp.se/job/betrcode/job/ScrumBoardSimulator/)
This job is triggered automatically by a Jenkins hook on GitHub.

## Non-standard IntelliJ plugins used
* Lombok

## Copyright
All rights reserved at this point. Have not decided what to do with this yet.

## Authors
Max Wenzin & Oscar Lantz


