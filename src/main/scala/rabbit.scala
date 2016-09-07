//create an App wrapped to start it all up
object Starter extends App {
  /*
   * This is the RabbitConnection Object
   * That will manage our queue connection
   */
  object RabbitConnection {
    val server = "http://localhost"
    val port = 15672
    val connectionString = s"$server/$port"

    /*
     * Setups a connection to queues
     */
    def Initialize() = {
    //  val connection = new ConnectionFactory()
      println("Hey im intialized thru sbt!")
    }
  }

  //initialize that RabbitMQ connection
  RabbitConnection.Initialize()
}
