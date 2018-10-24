package Theorem

import Theorem.syntax.ShowSyntax

trait Show[T] {
  def show(value: T): String

  val showSyntax = new ShowSyntax[T] { def T = Show.this}
}

object Show {
  @inline def apply[T](implicit ev: Show[T]): Show[T] = ev

  def show[A](fn: A => String): Show[A] = new Show[A] {
    override def show(a: A): String = fn(a)
  }
}


