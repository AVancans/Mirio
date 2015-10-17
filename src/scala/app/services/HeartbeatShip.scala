package app.services

import akka.actor.ActorRef
import app.features.ServiceType
import app.features.ServiceType.ServiceType
import app.features.{ServiceType, Service}
import app.models.Address
import app.{RootActor, Event}

import scala.collection.mutable
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._

import RootActor.system.dispatcher

class HeartbeatShip extends Service with HeartbeatService {
  override def SERVICE_TYPE: ServiceType = ServiceType.HEARTBEAT_SHIP
  override def SERVICE_ADDRESS: Address = Address("127.0.0.1", 8081)

  var serviceRegistryActor:ActorRef = null

  val waitingForKeys: mutable.Map[String, String] = mutable.Map()

  override def receive = {
    case StartService() =>
      serviceRegistryActor = sender()
      serviceRegistryActor ! ListServicesEvent()
      RootActor.system.scheduler.schedule(10 seconds, 10 seconds, self, ListLostServices())
    case ListServicesResponse(list) => {
      for (path <- list) {
          val key = java.util.UUID.randomUUID.toString
          waitingForKeys.put(key, path)
          RootActor.system.actorSelection(path) ! HeartBeatPing(key)
      }
    }
    case HeartBeatPong(key) => {
      waitingForKeys.remove(key)
    }
    case HeartBeatPing(key) => sender ! HeartBeatPong(key)
    case ListLostServices() => for (service <- waitingForKeys.values) println("Have not received PONG from" + service)


  }



}

case class StartService() extends Event
case class HeartBeatPing(key:String) extends Event
case class HeartBeatPong(key:String) extends Event
case class ListLostServices() extends Event
