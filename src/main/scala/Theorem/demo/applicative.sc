import scalaz.Applicative
import scalaz.std.list._
import scalaz.std.option._

val values = List(Some(1), Some(2), Some(3))
val funcs: List[Int => Int] = List(_ + 1, _ * 2, _ / 3)
val funcsO = funcs.map(Some(_))

Applicative[List].compose[Option].apply2(values, funcsO)((value, fn) => fn(value))
Applicative[Option].traverse(values)(value => value)