package example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global // DON'T DO THIS!

/**
  * Example #0: decomposing for-comprehension
  */
object Example0 {

  def initialValue(): Future[Char] = Future { 'a' }
  def increment(x: Char): Future[Int] = Future { x.toInt+1 }
  def wrapAnswer(y: Int): Future[String] = Future { "ans = " + y }

  def main(args: Array[String]) {

    // With for-comprehension
    val result1: Future[String] = for {
      x <- initialValue()
      y <- increment(x)
      z <- wrapAnswer(y)
    } yield z.reverse

    // Equivalent
    val result2: Future[String] = initialValue().flatMap {
      x => increment(x).flatMap {
        y => wrapAnswer(y).map {
          z => z.reverse
        }
      }
    }

    // Or more subtly...
    val result3: Future[String] = initialValue() flatMap increment flatMap wrapAnswer map (_.reverse)

    result1 onComplete println
    result2 onComplete println
    result3 onComplete println

    Thread sleep 1000
  }

}
