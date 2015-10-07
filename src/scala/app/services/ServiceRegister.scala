package app.services

import app.{Event, Config, TextEvent}
import app.features.ServiceType.ServiceType
import app.features.{ServiceType, Service}
import app.features.ServiceType.ServiceType
import app.models.Address
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.TypeImports
import com.mongodb.casbah.commons.Imports

class ServiceRegister extends Service {
  override def SERVICE_TYPE: ServiceType = ServiceType.SERVICE_REGISTRY
  override def SERVICE_ADDRESS: Address = Address("127.0.0.1", 8080)

  val appConfig = Config.get("app")
  val mongoConfig =  Config.get("mongo")

  implicit def jsonToMongo[A](x: (String, A)): TypeImports.DBObject = MongoDBObject(x)

  private val mongoIP: String = mongoConfig.getString("ip")
  private val mongoPort: Int = mongoConfig.getInt("port")
  private val appName = appConfig.getString("name")

  lazy val serviceCol = MongoClient(mongoIP, mongoPort)(appName)(SERVICE_TYPE.toString)

  override def receive = {
    case RegisterServiceEvent(service, id) => serviceCol.insert(id->("service"->service))
    case ListServicesEvent() => for (el <- serviceCol) println(el)
  }


}

case class RegisterServiceEvent(service:String, id:String) extends Event
case class ListServicesEvent() extends Event