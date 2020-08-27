package ir.pr.saman.service.morse.consumer.modules

import ir.pr.saman.service.morse.consumer.contract.service.ConsumeMorseObjectService
import ir.pr.saman.service.morse.consumer.usecase.ConsumeMorseObjectUseCase

object ServiceModule {

  val consumeMorseObjectService: ConsumeMorseObjectService = ConsumeMorseObjectUseCase

}
