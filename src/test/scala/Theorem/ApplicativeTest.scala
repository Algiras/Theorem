package Theorem

import Theorem.laws.ApplicativeLaws
import Theorem.std.list._
import Theorem.syntax.applicative._
import org.specs2._

class ApplicativeTest extends mutable.Specification {
  "Applicative" should {
    "Examples" should {
      "provide a version for basic types" >> {
        List(1, 2, 3).ap(List[Int => Int](v => v + 1, v => v + 2)) == List(2, 3, 3, 4, 4, 5)
        true
      }
    }
    "Laws" should {
      "work for basic types" >> {
        ApplicativeLaws.allApplicativeLaws[List, Int, Int](List(1, 2, 3), 1, _ + 1)
      }
      "laws that don't match up" >> {
        implicit val badApplicative: Applicative[Seq] = new Applicative[Seq] {
          override def pure[A](value: A): Seq[A] = Seq(value, value)


          override def ap[A, B](fn: Seq[A => B])(f: Seq[A]): Seq[B] = for {
            v <- f
            func <- fn
          } yield func(v)
        }

        !ApplicativeLaws.allApplicativeLaws[Seq, Int, Int](Seq(1, 2, 3), 1, _ + 1)
      }
    }
  }
}
