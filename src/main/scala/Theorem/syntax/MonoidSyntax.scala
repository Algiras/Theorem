package Theorem.syntax

import Theorem.Monoid

class MonoidOps[F](F: F)(implicit ev: Monoid[F]) {
  def zero: F = ev.zero
}
trait ToMonoidOps extends ToSemigroupOps{
  implicit def ToMonoidOps[F](v: F)(implicit F0: Monoid[F]): MonoidOps[F] = new MonoidOps[F](v)

  def mzero[F](implicit ev: Monoid[F]): F = ev.zero
}

trait MonoidSyntax[F] {
  def F: Monoid[F]

  implicit def ToMonoidOps(v: F): MonoidOps[F] = new MonoidOps[F](v)(MonoidSyntax.this.F)

  def mzero(implicit ev: Monoid[F]): F = ev.zero
}