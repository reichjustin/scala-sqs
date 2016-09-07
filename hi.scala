object Starter extends App {

  class Justin(age: Int) extends Person(age: Int) {
      val name = "Justin"
      val a = age
  }

  var p = new Justin(30)
  println(p.name)
  println(p.a)
}

abstract class Person(age: Int) {
  val name: String
}
