package Theorem.std

import Theorem.{Monoid, Semigroup, Show}

trait StringInstances {
  implicit object StringInstances extends Show[String] with Semigroup[String] with Monoid[String]{
    override def show(value: String): String = value

    override def append(left: String, right: => String): String = left ++ right

    override def zero: String = ""
  }
}

object string extends StringInstances {}
