import awscala.sqs.Message
import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.collection.immutable.Document

object MongoConnection extends DatabaseConnection {
  val connectionString = "mongodb://localhost"

  def Save(msg: Message) = {
    //print out
    val doc = Document("Message" -> msg.toString)
    println(doc.toString)

    val client = MongoClient(connectionString)

    val db = client.getDatabase("messages")
    val collection = db.getCollection("msgs")

    val result = collection.insertOne(doc).foreach(comp => println(comp))
  }
}

trait DatabaseConnection {
  val connectionString: String

  def Save(msg: Message)
}
