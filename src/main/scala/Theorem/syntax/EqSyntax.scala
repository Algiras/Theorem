package Theorem.syntax

import Theorem.Eq

final class EqOps[T](self: T)(implicit val T: Eq[T]) {
  def ===(right: T): Boolean = T.===(self, right)
  def =/=(right: T): Boolean = T.=/=(self, right)
}

trait ToEqOps {
  implicit def ToEqOps[T](value: T)(implicit F0: Eq[T]): EqOps[T] = new EqOps[T](value)
}

trait EqSyntax[T] {
  def T: Eq[T]

  implicit def isEqOps(value: T): EqOps[T] = new EqOps[T](value)(EqSyntax.this.T)
}
