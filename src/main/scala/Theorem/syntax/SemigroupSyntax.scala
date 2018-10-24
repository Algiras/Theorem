package Theorem.syntax

import Theorem.Semigroup

final class SemigroupOps[T](val self: T)(implicit val ev: Semigroup[T]) {
  def mappend(right: => T): T = ev.append(self, right)
  def |+|(right: => T): T = ev.append(self, right)
}

trait ToSemigroupOps {
  implicit def ToSemigroupOps[T](v: T)(implicit F0: Semigroup[T]): SemigroupOps[T] = new SemigroupOps[T](v)
}

trait SemigroupSyntax[T] {
  def T: Semigroup[T]

  implicit def ToSemigroupOps(v: T): SemigroupOps[T] = new SemigroupOps[T](v)(SemigroupSyntax.this.T)

  def mappend(f1: T, f2: => T)(implicit T: Semigroup[T]) : T = T.append(f1, f2)
}