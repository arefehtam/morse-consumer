package ir.pr.saman.service.morse.consumer

import com.typesafe.scalalogging.Logger
import ir.pr.saman.service.morse.consumer.modules.ServiceModule.consumeMorseObjectService
import ir.pr.saman.service.morse.consumer.modules.config.akka.ActorModule.ec
import org.json4s.DefaultFormats
import org.json4s.jackson.Json

import scala.concurrent.Future
/**
  * Application is the main object. It shows a morse object received from the queue as long as there is any.
  * Each time a producer sends a new object, a new print is appeared in console
  * any error with is this process is captured and shown
  */
object Application extends App {

  val logger = Logger("Main Application")
  val jsonFormat = Json(DefaultFormats)

  consumeMorseObjectService call 0 recoverWith {
    case err => logger error err.getMessage
      Future failed err
  }

}
