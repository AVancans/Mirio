package app



import akka.actor._
import app.services._

import com.typesafe.scalalogging.{Logger, LazyLogging}
import org.apache.log4j.xml.DOMConfigurator


object Boot extends App  {

//  DOMConfigurator.configure("log4j.xml");

  val a = ClassLoader.getSystemResource("/log4j.xml")

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
