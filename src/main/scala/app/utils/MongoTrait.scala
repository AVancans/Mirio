package app.utils

import app.Config
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.TypeImports

trait MongoTrait {

  val appConfig = Config.get("app")
  val mongoConfig =  Config.get("mongo")

  implicit def jsonToMongo[A](x: (String, A)): TypeImports.DBObject = MongoDBObject(x)

  def mongoIP: String = mongoConfig.getString("ip")
  def mongoPort: Int = mongoConfig.getInt("port")
  def appName = appConfig.getString("name")

}
