package Theorem.std

import Theorem.{Eq, Show}

trait booleanInstances {

  implicit object BooleanInstance extends Show[Boolean] {
    override def show(value: Boolean): String = {
      if (value) {
        "true"
      } else {
        "false"
      }
    }
  }

  implicit val booleanEq = Eq.equalA[Boolean]

}

object boolean extends booleanInstances {}