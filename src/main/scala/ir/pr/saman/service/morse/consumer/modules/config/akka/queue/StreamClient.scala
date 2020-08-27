package ir.pr.saman.service.morse.consumer.modules.config.akka.queue

import ir.pr.saman.service.morse.consumer.modules.config.ConfigModule

object StreamClient extends ConfigModule {

  lazy val asyncFactor = config getInt "akka.queue.client.rabbit-mq.stream.async-factor"
  lazy val bufferSize = config getInt "akka.queue.client.rabbit-mq.stream.buffer-size"
  lazy val host = config getString "akka.queue.client.rabbit-mq.host"
  //  lazy val vhost = config getString "akka.queue.client.rabbit-mq.vhost"
  lazy val password = config getString "akka.queue.client.rabbit-mq.password"
  //  lazy val port = config getInt "akka.queue.client.rabbit-mq.port"
  lazy val queueName = config getString "akka.queue.client.rabbit-mq.name"
  lazy val user = config getString "akka.queue.client.rabbit-mq.user"

}
