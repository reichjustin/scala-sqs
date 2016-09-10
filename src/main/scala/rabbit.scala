import awscala._
import awscala.sqs._
import rx.lang.scala.Observable
import scala.concurrent.duration._


//create an App wrapped to start it all up
object Starter extends App {

  /*
   * This is the RabbitConnection Object
   * That will manage our queue connection
   */
  object RabbitConnection extends QueueConnection {

    /*
     * Setups a connection to queues
     */
    def Initialize() = {
      val sqs = SQS.at(Region.US_WEST_2)
      val queue = sqs createQueueAndReturnQueueName  "justin-test"
        
      //setup a read every 500 milliseconds using RxScala
      val queueReader = Observable.interval(500 millis)
      queueReader.subscribe(qr => ReadMessage(queue,sqs))
    }

    //read the message from the queue
    def ReadMessage(queue: Queue,sqs: SQS) = {
      val returnQueue = sqs createQueueAndReturnQueueName  "return-queue"
      //setup a connection to the queue
      sqs.withQueue(queue) { s =>
        //each message received loop thru
        s.receiveMessage().foreach(m => {
          //save to mongo
        //  MongoConnection.Save(m, "msgs")
          dynamo.Save(m)
          //delete from the queue
          sqs.deleteMessage(m)

          //send a message to another queue just for shitz and gigz
          sqs.send(returnQueue, "process-message")
        })
      }
    }
  }

  //initialize that RabbitMQ connection and Mongo Connection
 // MongoConnection.InitializeConnection()
  RabbitConnection.Initialize()
}

//a generic QueueConnection trait
trait  QueueConnection {
  def Initialize(): Unit
  def ReadMessage(queue: Queue,sqs: SQS)
}
