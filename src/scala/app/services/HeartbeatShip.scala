package app.services

import app.features.ServiceType
import app.features.ServiceType.ServiceType
import app.features.{ServiceType, Service}
import app.models.Address

class HeartbeatShip extends Service with HeartbeatService {
  override def SERVICE_TYPE: ServiceType = ServiceType.HEARTBEAT_SHIP
  override def SERVICE_ADDRESS: Address = Address("127.0.0.1", 8081)
}
