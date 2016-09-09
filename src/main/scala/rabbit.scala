import awscala._, sqs._


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
    val accessKey = "<ADD_KEY>"
    val secret = "<ADD_SECRET>"


    /*
     * Setups a connection to queues
     */
    def Initialize() = {
      implicit val sqs = SQS.at(Region.US_WEST_2)
      val queue = sqs createQueueAndReturnQueueName  "justin-test"

      val msg = scala.io.StdIn.readLine("What do you want to send: ")
      queue.add(msg)
    }

//    def SendMessage(msg: String, queue: Queue) = queue.add(msg)
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
}
