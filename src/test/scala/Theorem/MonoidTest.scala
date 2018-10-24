package Theorem

import org.specs2._
import Theorem.std.int._
import Theorem.std.string._
import Theorem.std.list._
import Theorem.syntax.monoid._
import Theorem.laws.MonoidLaws._

class MonoidTest extends mutable.Specification {
  "Monoid" should {
    "Examples" should {
      "provide a version for basic types" >> {
        Monoid[Int].append(1, Monoid[Int].zero) == 1.mappend(Monoid[Int].zero)
        1.mappend(Monoid[Int].zero) == 1
        "ABC".mappend("".zero) == "ABC"
        List(1, 2, 3).mappend(Monoid[List[Int]].zero) == List(1, 2, 3)
      }
    }
    "Laws" should {
      "work for basic types" >> {
        allMonoidLaws(1, 2, 3)
        allMonoidLaws(List(1), List(2), List(3))
      }
      "laws that don't match up" >> {
        implicit val incorrectMonoid: Monoid[Double] = new Monoid[Double] {
          override def zero: Double = 1

          override def append(left: Double, right: => Double): Double = left + right
        }

        allMonoidLaws(1.2, 2.1, 1.2)
      }
    }
  }
}
