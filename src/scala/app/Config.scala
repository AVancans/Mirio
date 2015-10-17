package app

import java.io.File

import com.typesafe.config.ConfigFactory

object Config {

  private val conf = ConfigFactory.defaultApplication()

  def get(key:String) = conf.getConfig(key)

}
