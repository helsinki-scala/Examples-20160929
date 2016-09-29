package example

import scala.collection.immutable.{HashSet, TreeSet}
import scala.reflect.runtime.universe._

/**
  * Example #2: Guess the type after getOrElse
  */
object Example2 {

  def realType[T](t: T): String = t.getClass.getTypeName
  def valType[T : TypeTag](t : T): String = implicitly[TypeTag[T]].tpe.toString

  def main(args: Array[String]) {

    val a = Option("foo") getOrElse "bar"
    val b = Option("foo") getOrElse 42
    val c = Option(TreeSet(1)) getOrElse HashSet(2)
    val d = Option("foo") getOrElse { throw new Exception("kaboom!") }
    val e = None getOrElse "foo"
    val f = (None : Option[Int]) getOrElse "foo"
    val g = Option("") getOrElse Option("")
    val h = Option(List(0)) getOrElse Set(0)
    val i = Option(List()) getOrElse Set()
    val j = Option(Map) getOrElse Set
    val k = None getOrElse Some
    val l = None getOrElse None getOrElse ()

    // Print the answers
    println(Iterator.fill(140)('=').mkString(""))
    printf("%4s %45s %65s\n", "val", "runtime type", "observed type")
    println(Iterator.fill(140)('=').mkString(""))
    printf("%4s %45s %65s\n", "a", realType(a), valType(a))
    printf("%4s %45s %65s\n", "b", realType(b), valType(b))
    printf("%4s %45s %65s\n", "c", realType(c), valType(c))
    printf("%4s %45s %65s\n", "d", realType(d), valType(d))
    printf("%4s %45s %65s\n", "e", realType(e), valType(e))
    printf("%4s %45s %65s\n", "f", realType(f), valType(f))
    printf("%4s %45s %65s\n", "g", realType(g), valType(g))
    printf("%4s %45s %65s\n", "h", realType(h), valType(h))
    printf("%4s %45s %65s\n", "i", realType(i), valType(i))
    printf("%4s %45s %65s\n", "j", realType(j), valType(j))
    printf("%4s %45s %65s\n", "k", realType(k), valType(k))
    printf("%4s %45s %65s\n", "l", realType(l), valType(l))
    println(Iterator.fill(140)('=').mkString(""))
  }

}
