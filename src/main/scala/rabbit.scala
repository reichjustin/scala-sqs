import awscala._
import awscala.sqs._
import rx.lang.scala.Observable
import scala.concurrent.duration.Duration


//create an App wrapped to start it all up
object Starter extends App {

  /*
   * This is the RabbitConnection Object
   * That will manage our queue connection
   */
  object RabbitConnection extends QueueConnection {
    //implement the QueueConnection val's
    val server = "http://localhost"
    val port = 15672
    val connectionString = s"$server/$port"
    val accessKey = "AKIAJDHE5W64BAF55GLQ"
    val secret = "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"

    /*
     * Setups a connection to queues
     */
    def Initialize() = {
      implicit val sqs = SQS.at(Region.US_WEST_2)
      val queue = sqs createQueueAndReturnQueueName  "justin-test"

      //setup a read every 500 milliseconds using RxScala
      val queueReader = Observable.interval(Duration.create("500 millis"))
      queueReader.subscribe(qr => ReadMessage(queue,sqs))
    }

    //read the message from the queue
    def ReadMessage(queue: Queue,sqs: SQS) = {
      //setup a connection to the queue
      sqs.withQueue(queue) { s =>
        //each message received loop thru
        s.receiveMessage().foreach(m => {
          //print out
          println(m.body.toString)

          //delete from the queue
          sqs.deleteMessage(m)
        })
      }
    }
  }

  //initialize that RabbitMQ connection
  RabbitConnection.Initialize()
}

//a generic QueueConnection trait
trait  QueueConnection {
  val server: String
  val port: Int
  val connectionString: String
  val accessKey: String
  val secret: String

  def Initialize(): Unit
  def ReadMessage(queue: Queue,sqs: SQS)
}
