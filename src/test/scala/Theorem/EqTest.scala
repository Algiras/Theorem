package Theorem

import Theorem.laws.EqLaws._
import org.specs2._
import Theorem.std.int._
import Theorem.std.list._
import Theorem.std.boolean._
import Theorem.syntax.eq._

class EqTest extends mutable.Specification {
  "Eq" should {
    "Examples" should {
      "provide a version for basic types" >> {
        Eq[Int].===(Monoid[Int].zero, Monoid[Int].zero)
        1 =/= 2
      }
    }
    "Laws" should {
      "work for basic types" >> {
          allEqLaws[Int, Int](1, 1, 1, _ + 1) and
          allEqLaws[List[Int], Boolean](List(1), List(1), List(1), _.contains(1))
      }
      "laws that don't match up" >> {
        implicit val BadEq: Eq[Double] = (_: Double, _: Double) => false

        ! allEqLaws[Double, Double](1.1, 1.1, 2.2, _ + 1.0)
      }
    }
  }
}
