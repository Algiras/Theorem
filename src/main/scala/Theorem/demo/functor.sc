import scalaz.Functor
import scalaz.std.option._
import scalaz.std.list._

val add1 = (value : Int) => value + 1

List(1, 2, 3).map(add1)
Option(1).map(add1)
Functor[List].compose[Option].map(List(Some(1), Some(2)))(add1)

