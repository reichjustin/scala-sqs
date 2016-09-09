name := """read-from-rabbit"""
version := "1.0"
scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
    "com.github.seratch" %% "awscala" % "0.5.+",
    "io.reactivex" %% "rxscala" % "0.26.2"
)