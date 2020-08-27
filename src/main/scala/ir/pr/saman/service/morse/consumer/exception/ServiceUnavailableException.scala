package ir.pr.saman.service.morse.consumer.exception

case class ServiceUnavailableException(what: String) extends Exception {

  override def getMessage: String = what + " is not available"
}
