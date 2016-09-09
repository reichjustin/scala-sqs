import awscala.sqs._
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongoConnection extends DatabaseConnection {
  val connectionString = "mongodb://localhost"
  var db: MongoDatabase = null

  def InitializeConnection = {
    val client = MongoClient(connectionString)
    db = client.getDatabase("messages")
  }

  def Save(msg: Message, collectionName: String) = {
    val doc = Document("Message" -> msg.body)
    val collection = db.getCollection(collectionName)

    collection.insertOne(doc).foreach(comp => println(comp.toString()))
  }
}

trait DatabaseConnection {
  val connectionString: String
  var db: MongoDatabase

  def Save(msg: Message, collectionName: String)
  def InitializeConnection()
}
