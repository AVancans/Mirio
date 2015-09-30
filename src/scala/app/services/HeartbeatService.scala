package app.services
import app.Event

trait HeartbeatService  {

  def handleHeartbeat(event:Event):String = {
    Status.HEALTHY
  }

}

object Status {
  val HEALTHY: String = "GREEN"
  val DELAYED : String = "DELAYED"
  val DEAD : String = "DEAD"
  val RESTARTING : String = "RESTARTING"
  val STARTING : String = "STARTING"
  val UNKNOWN : String = "UNKNOWN"
}
