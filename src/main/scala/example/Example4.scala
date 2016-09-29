package example

import akka.actor.Status.Failure
import akka.actor.{Props, ActorSystem, Actor}
import example.Example4.ExampleActor.{JobDone, JobRequest}

import scala.concurrent.Future
import scala.util.Random

/**
  * Example #4: Futures and Actors
  */
object Example4 {

  class ExampleActor extends Actor {

    import akka.pattern._
    import context.dispatcher

    def handle(req: JobRequest): Future[JobDone] = {
      Future {
        Thread sleep Random.nextInt(5000)
        if (Random.nextBoolean())
          throw new Exception(s"something went wrong with ${req.client}")
        JobDone(req.client)
      }
    }

    def receive = {
      case req @ JobRequest(client) =>
        println(s"starting job from $client")
        handle(req) pipeTo self
      case JobDone(client) =>
        println(s"job from $client is done")
      case Failure(cause) => // this is not Failure from Try!!! See the imports!
        println(s"job failed: $cause")
    }

  }

  object ExampleActor {
    case class JobRequest(client: String)
    case class JobDone(client: String)
  }

  def main(args: Array[String]) {
    val system = ActorSystem("sys")
    val exampleActor = system.actorOf(Props[ExampleActor], "example")

    while (true) {
      exampleActor ! JobRequest(Random.nextInt(100).toString)
      Thread sleep 500
    }
  }

}
