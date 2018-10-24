package Theorem.std

import Theorem.{Eq, Monoid, Semigroup, Show}

trait IntInstances {
  implicit object IntInstances extends Show[Int] with Semigroup[Int] with Monoid[Int]{
    override def show(value: Int): String = value.toString

    override def append(left: Int, right: => Int): Int = left + right

    override def zero: Int = 0
  }

  implicit val intEq = Eq.equalA[Int]
}

object int extends IntInstances {}
