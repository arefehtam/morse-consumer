package ir.pr.saman.service.morse.consumer.contract.service

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

trait Service[In, Out] {

  def call(body: In)(implicit ec: ExecutionContext): Future[Out]

}
