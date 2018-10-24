package Theorem.syntax

import Theorem.Show

final class ShowOps[T](val self: T)(implicit val ev: Show[T]) {
  def show = ev.show(self)
}

trait ToShowOps {
  implicit def ToShowOps[T](v: T)(implicit F0: Show[T]): ShowOps[T] = new ShowOps[T](v)
}

trait ShowSyntax[T] {
  def T: Show[T]

  implicit def ToShowOps(v: T) : ShowOps[T] = new ShowOps[T](v)(ShowSyntax.this.T)
}