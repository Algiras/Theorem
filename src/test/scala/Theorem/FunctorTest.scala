package Theorem

import Theorem.laws.FunctorLaws
import org.specs2._
import Theorem.std.list._

class FunctorTest extends mutable.Specification {
  "Functor" should {
    "Examples" should {
      "provide a version for basic types" >> {
        Functor[List].map(List(1, 2, 3))(_ + 1) must_=== List(2, 3, 4)
      }
    }
    "Laws" should {
      "work for basic types" >> {
        FunctorLaws.allFunctorLaws[List, Int, Int, String](List(1, 2, 3), _ + 2, _.toString)
      }
      "laws that don't match up" >> {
        implicit val BadFunctor: Functor[Seq] = new Functor[Seq] {
          override def map[A, B](fa: Seq[A])(fn: A => B): Seq[B] = fa.map(fn).reverse
        }
        FunctorLaws.allFunctorLaws[Seq, Int, Int, String](Seq(1, 2, 3), _ + 2, _.toString) must beFalse
      }
    }
  }
}
