package app

import java.io.File

import com.typesafe.config.ConfigFactory

object Config {

  private val conf = ConfigFactory.parseFile(new File("src/scala/app/conf/application.conf"))

  def get(key:String) = conf.getConfig(key)

}
