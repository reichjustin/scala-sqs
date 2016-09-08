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

    /*
     * Setups a connection to queues
     */
    def Initialize() = {
      println("RabbitConnection.Initialize()")
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

  def Initialize()
}
