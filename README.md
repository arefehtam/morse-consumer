# Morse-Consumer

In order to run this project(preferably install InteliJIdea community edition):

* Install `sbt`. (For more info, click [here] [1])
* In case InteliJ has been installed:
    * Set Project Structure (ctl + alt + shift + s):
        * Click on `+` to add new module
        * Select `Import Module` and navigate to where you clone the project and choose `morse-consumer`
    * In the Project Structure, switch to project tab, make sure the project has default JDK(prefer jdk-1.8.x)    
* Copy 'application.template.conf' and rename it as 'application.conf'  
* Change the config in `application.conf` 
* Run `sbt clean compile` at project root
* Make sure the file `application.conf` is copied in target path: target/scala-2*/classes, otherwise,
copy it manually
* Rub `sbt run` at project root
* Instead of cli running, you can find the main project `Application.scala`, then right click and choose `Run 'Application'`
* When running the project, in console you see a question to enter you query string

#Prerequisite

* Install `rabbitmq`, config and start it's service 
* Make sure the queue is not empty so that ou can see the output printed in console
* In this version, port of rabbitmq is default at `15672` and can not be changed

[1]: https://www.scala-sbt.org/1.x/docs/Setup.html
