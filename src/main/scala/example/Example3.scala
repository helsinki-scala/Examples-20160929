package example

import java.lang.Runtime
import java.util.concurrent.{Executors, ExecutorService}
import scala.concurrent.duration._
import scala.concurrent._

import scala.concurrent.ExecutionContext.Implicits.global // DON'T DO THIS!

/**
  * Example #3: Futures as workers
  */
object Example3 {

  def foo(i: Int): String = {
    Thread sleep 5000
    s"foo$i"
  }

  def bar(i: Int): String = {
    s"bar$i"
  }

  def main(args: Array[String]): Unit = {
    val fooJobs = Future sequence { for (i <- 1 to 8) yield { Future { foo(i) } } }
    val barJobs = Future sequence { for (i <- 1 to 8) yield { Future { bar(i) } } }

    fooJobs onComplete println
    barJobs onComplete println

    Thread.currentThread().join()
  }

}
