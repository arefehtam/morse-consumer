package ir.pr.saman.service.morse.consumer.modules.config.akka

import akka.actor.ActorSystem
import com.typesafe.config.Config
import ir.pr.saman.service.morse.consumer.modules.config.ConfigModule

import scala.concurrent.ExecutionContext

object ActorModule extends ConfigModule {

  val conf: Config = config.getConfig("akka.http.server.preview").withFallback(config.resolve())

  implicit val actorSystem: ActorSystem = ActorSystem("dbSystem", conf)
  implicit val ec: ExecutionContext = actorSystem.dispatcher
}
