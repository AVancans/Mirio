package app.services

import akka.actor.ActorRef
import app.utils.MongoTrait
import app.{RootActor, Event, Config, TextEvent}
import app.features.ServiceType.ServiceType
import app.features.{ServiceType, Service}
import app.features.ServiceType.ServiceType
import app.models.Address
import com.mongodb.casbah
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.TypeImports
import com.mongodb.casbah.commons.Imports

class ServiceRegister extends Service with MongoTrait {
  override def SERVICE_TYPE: ServiceType = ServiceType.SERVICE_REGISTRY
  override def SERVICE_ADDRESS: Address = Address("127.0.0.1", 8080)


  lazy val serviceCol = MongoClient(mongoIP, mongoPort)(appName)(SERVICE_TYPE.toString)

  override def receive = {
    case RegisterServiceEvent(service, id) => serviceCol.insert(MongoDBObject("service"->id,"path"->service.path.toString))
    case ListServicesEvent() => {
      val toList = serviceCol.find("path" $exists true)
      val strings: Iterator[String] = for (x <- toList) yield x.getAsOrElse[String]("path", "").toString
      sender ! ListServicesResponse(strings)
    }
    case HeartBeatPing(key) => sender ! HeartBeatPong(key)
  }


}

case class RegisterServiceEvent(service:ActorRef, id:String) extends Event
case class ListServicesEvent() extends Event
case class ListServicesResponse(services:Iterator[String]) extends Event