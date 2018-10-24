package Theorem.syntax

import Theorem.Applicative

final class ApplicativeOps[F[_], A](self: F[A])(implicit val F: Applicative[F]){
  def pure[B](value: B): F[B] = F.pure(value)
  def map[B](fn: A => B): F[B] = F.map(self)(fn)
  def ap[B](fn: F[A => B]): F[B] = F.ap(fn)(self)
}

trait ToApplicativeOps {
  implicit def ToApplicativeOps[F[_], A](value: F[A])(implicit F0: Applicative[F]): ApplicativeOps[F, A] = new ApplicativeOps[F, A](value)
}

trait ApplicativeSyntax[F[_]] {
  def F: Applicative[F]

  implicit def ToApplicativeOps[A](v: F[A]): ApplicativeOps[F, A] = new ApplicativeOps[F, A](v)(ApplicativeSyntax.this.F)
}
