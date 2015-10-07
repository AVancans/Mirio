package app


import java.io.File
import com.mongodb.casbah.Imports._

import akka.actor._
import app.services.{ListServicesEvent, RegisterServiceEvent, ServiceRegister}
import com.typesafe.config.ConfigFactory

object Boot extends App {

  println("App ready!")



  val registerActor = RootActor.system.actorOf(Props[ServiceRegister], "ServiceRegister")
  val eventStream =  new EventStream

  eventStream.subscribe(registerActor, "greetings")

  registerActor ! RegisterServiceEvent("Main", "12346")
  registerActor ! ListServicesEvent()

}

object RootActor {
  val system = ActorSystem.create("MainSystem")
  val inbox = Inbox.create(system)
}
