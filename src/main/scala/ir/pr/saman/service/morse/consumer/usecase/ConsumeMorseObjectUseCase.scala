package ir.pr.saman.service.morse.consumer.usecase

import java.net.ConnectException

import akka.Done
import akka.stream.alpakka.amqp.AmqpUriConnectionProvider
import akka.stream.alpakka.amqp.NamedQueueSourceSettings
import akka.stream.alpakka.amqp.QueueDeclaration
import akka.stream.alpakka.amqp.ReadResult
import akka.stream.alpakka.amqp.scaladsl.AmqpSource
import com.typesafe.scalalogging.Logger
import ir.pr.saman.service.morse.consumer.contract.service.ConsumeMorseObjectService
import ir.pr.saman.service.morse.consumer.domain.MorseObject
import ir.pr.saman.service.morse.consumer.exception.ServiceUnavailableException
import ir.pr.saman.service.morse.consumer.modules.config.akka.ActorModule.actorSystem
import ir.pr.saman.service.morse.consumer.modules.config.akka.queue.StreamClient._
import org.json4s.DefaultFormats
import org.json4s.jackson.Json

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait ConsumeMorseObjectUseCase extends ConsumeMorseObjectService {

  val logger = Logger("Consumer")
  val jsonFormat = Json(DefaultFormats)

  /**
    * When there is any message on queue, consumer processes that message and print it
    *
    * @param count: for retrying the connection to rabbitmq
    * @param ec: implicit execution context for asynchronous communication with morse producer service
    * @return Done: this is the akka lib object in case of successful operation
    * In case of connection error, ServiceUnavailableException object is returned,
    * For other errors, their corresponding object is returned
    */

  override def call(count: Int = 0)(implicit ec: ExecutionContext): Future[Done] = {
    val connectionProvider = AmqpUriConnectionProvider(s"amqp://$user:$password@$host")
    val qName = queueName //+ System.currentTimeMillis()
    val queueDeclaration = QueueDeclaration(qName)
    val amqpSource = AmqpSource.atMostOnceSource(
      NamedQueueSourceSettings(connectionProvider, queueName)
        .withDeclaration(queueDeclaration)
        .withAckRequired(false),
      bufferSize = bufferSize
    )

    logger info "Consuming from RabbitMQ"

    amqpSource
      .map(message => createMessage(message))
      .runForeach(message => logger info jsonFormat.write(message))
      .recoverWith {
        case exp: ConnectException => if (count < 10) {
          logger info s"Retrying Establishing Connection: $count"
          Thread.sleep(10000)
          call(count + 1)
        } else {
          Future failed ServiceUnavailableException(exp + "\n" + queueName)
        }
        case e: Throwable => Future failed e

      }

  }

  private def createMessage(body: ReadResult): MorseObject = {
    val value = body.bytes.utf8String
    val r = jsonFormat.read[MorseObject](value)
    r
  }

}

object ConsumeMorseObjectUseCase extends ConsumeMorseObjectUseCase
