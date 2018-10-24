package Theorem

import Theorem.laws.MonadLaws
import Theorem.std.list._
import org.specs2._

class MonadTest extends mutable.Specification {
  "Monad" should {
    "Examples" should {
      "provide a version for basic types" >> {
        Monad[List].flatMap[Int, Int](List(1, 2, 3))(v => List(v, v)) == List(1, 1, 2, 2, 3, 3)
      }
    }
    "Laws" should {
      "work for basic types" >> {
        MonadLaws.allMonadLaws[List, Int, Int, String](1, v => List(v, v), v => List(v.toString, v.toString))
      }
      "laws that don't match up" >> {
        implicit val badMonad = new Monad[Seq]{
          override def flatMap[A, B](value: Seq[A])(fn: A => Seq[B]): Seq[B] = Seq.empty[B]

          override def pure[A](value: A): Seq[A] = Seq.empty[A]

          override def ap[A, B](fn: Seq[A => B])(f: Seq[A]): Seq[B] = Seq.empty[B]
        }

        !MonadLaws.allMonadLaws[Seq, Int, Int, String](1, v => List(v, v), v => List(v.toString, v.toString))
      }
    }
  }
}
