import Theorem.Monoid
import Theorem.std.string._
import Theorem.std.int._
import Theorem.syntax.monoid._

def sum[T: Monoid](values: T*): T = values.foldLeft(Monoid[T].zero)(_ |+| _)

sum("A", "B", "C")
sum(1, 2, 3)

sum[String]()
sum[Int]()