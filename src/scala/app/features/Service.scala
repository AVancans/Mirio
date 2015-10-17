package app.features

import akka.actor.Actor
import app.features.ServiceType.ServiceType
import app.models.Address
import app.services.Status

trait Service extends Actor {

  val SERVICE_ID:String = java.util.UUID.randomUUID.toString
  def SERVICE_TYPE:ServiceType
  def SERVICE_ADDRESS:Address
  val SERVICE_STATUS = Status.UNKNOWN


  def receive = {
    case _ => println("Received a message at " + SERVICE_TYPE)
  }

  override def unhandled(message:Any) = println("Message (" + message.toString + ") unhandled.")

}

object ServiceType extends Enumeration  {
  type ServiceType = Value
  val DISCOVERY_SHIP = Value("DISCOVERY_SHIP")
  val HEARTBEAT_SHIP = Value("HEARTBEAT_SHIP")
  val SERVICE_REGISTRY = Value("SERVICE_REGISTRY_")
}