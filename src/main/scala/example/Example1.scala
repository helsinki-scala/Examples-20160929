package example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global // DON'T DO THIS!

/**
  * Example #1: Future+Option in for-comprehension
  */
object Example1 {

  def main(args: Array[String]): Unit = {
    val f1 = Future { 42 }
    val f2 = Future { "foo" }
    val o1 = Some('X')

    for {
      x <- f1
      y <- f2
      z <- o1
    } {
      println(x, y, z)
    }

    Thread sleep 2000
  }

}
