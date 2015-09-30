package app.features

import app.models.Address
import app.services.Status

trait Service {

  val SERVICE_ID:String = java.util.UUID.randomUUID.toString
  def SERVICE_TYPE:String
  def SERVICE_ADDRESS:Address
  val SERVICE_STATUS = Status.UNKNOWN

}

object ServiceType extends Enumeration  {
  type ServiceType = Value
  val DISCOVERY_SHIP = Value("DISCOVERY_SHIP")
  val HEARTBEAT_SHIP = Value("HEARTBEAT_SHIP")
}