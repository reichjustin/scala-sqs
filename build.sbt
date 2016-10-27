name := """read-from-rabbit2"""
version := "1.1"
scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
    "com.github.seratch" %% "awscala" % "0.5.+",
    "io.reactivex" %% "rxscala" % "0.26.2",
    "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.1"
)
