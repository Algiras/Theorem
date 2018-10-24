package Theorem

import Theorem.laws.SemigroupLaws
import Theorem.syntax.semigroup._
import Theorem.std.int._
import Theorem.std.string._
import org.specs2._

class SemigroupTest extends mutable.Specification{

  "Semigroup" should {
    "Examples" should {
      "provide a version for basic types" >> {

        5.mappend(5) must_=== 10

        ("A" |+| "B") must_=== "AB"
      }
    }
    "Laws" should {
      "work for basic types" >> {
        SemigroupLaws.associativityLaw("A", "B", "C") and
        SemigroupLaws.associativityLaw(1, 2, 3)
      }
      "laws that don't match up" >> {
        implicit val DivisionSemigroup = new Semigroup[Double] {
          override def append(left: Double, right: => Double): Double = left / right
        }

        SemigroupLaws.associativityLaw(1.1, 2.2, 3.3) must beFalse
      }
    }
  }
}
