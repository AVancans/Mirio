package app



import akka.actor._
import app.services._

import com.typesafe.scalalogging.{Logger, LazyLogging}


object Boot extends App  {

  println("App ready!")

  val registerActor = RootActor.system.actorOf(Props[ServiceRegister], "ServiceRegister")
  val heartbeatShip = RootActor.system.actorOf(Props[HeartbeatShip], "HeartbeatShip")

  registerActor ! RegisterServiceEvent(heartbeatShip, "HeartBeat")

  heartbeatShip.tell(StartService(), registerActor)


}

object RootActor {
  val system = ActorSystem.create("MainSystem")
  val inbox = Inbox.create(system)
}
