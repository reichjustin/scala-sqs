import awscala._, dynamodbv2._

/**
  * Created by sql on 9/9/16.
  */
object dynamo {

 //this is a test - haha
  def Save(msg: sqs.Message) = {
    //this expects a dynamo config to exist another change
    implicit val dynamoDB = DynamoDB.at(Region.US_WEST_2)
    val table: Table = dynamoDB.table("sqs-msgs").get

    table.put(1,"Message" -> msg.body)
  }
}
