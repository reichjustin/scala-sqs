object Starter extends App {

  /*
   * This is the RabbitConnection Object
   * That will manage our queue connection
   */
  object RabbitConnection {
    val server = "http://localhost"
    val port = 15672
    val connectionString = s"$server/$port"

    def Initialize() = {
    //  val connection = new ConnectionFactory()
    }
  }

  //initialize that RabbitMQ connection
  RabbitConnection.Initialize()
}
