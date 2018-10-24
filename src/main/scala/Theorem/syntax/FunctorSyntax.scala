package Theorem.syntax

import Theorem.Functor

final class FunctorOps[F[_], A](val self: F[A])(implicit val F: Functor[F]) {
  def map[B](fn: A => B): F[B] = F.map(self)(fn)
}

trait ToFunctorOps {
  implicit def ToFunctorOps[F[_], A](v: F[A])(implicit F0: Functor[F]): FunctorOps[F, A] = new FunctorOps[F, A](v)
}

trait FunctorSyntax[F[_]] {
  def F: Functor[F]

  implicit def ToFunctorOps[A](v: F[A]): FunctorOps[F, A] = new FunctorOps[F, A](v)(FunctorSyntax.this.F)
}