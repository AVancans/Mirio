package app.services

import app.features.ServiceType.ServiceType
import app.features._
import app.models.Address

class DiscoveryShip extends Service with HeartbeatService {
  override def SERVICE_TYPE: ServiceType = ServiceType.DISCOVERY_SHIP
  override def SERVICE_ADDRESS: Address = Address("127.0.0.1", 8080)
}
