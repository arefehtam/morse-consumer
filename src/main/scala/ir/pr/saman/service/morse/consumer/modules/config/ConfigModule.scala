package ir.pr.saman.service.morse.consumer.modules.config

import com.typesafe.config.ConfigFactory
import com.typesafe.config.{Config => TypesafeConfig}

trait ConfigModule {

  val config: TypesafeConfig = ConfigFactory.load().withFallback(ConfigFactory.defaultApplication()).resolve()

}
