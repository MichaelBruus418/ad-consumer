package utils


import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success, Try}

object TestUtil {

  def main(): Unit = {
    val f = Future(Source.fromURL("http://localhost:9100/api/creative/ping"))
    f.onComplete {
      case Success(v) => println(v)
      case Failure(e) => println(e)
    }
  }
}
