package app


import java.io.File
import com.mongodb.casbah.Imports._

import akka.actor._
import app.services._
import com.typesafe.config.ConfigFactory
import sun.plugin2.message.HeartbeatMessage

object Boot extends App {

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
