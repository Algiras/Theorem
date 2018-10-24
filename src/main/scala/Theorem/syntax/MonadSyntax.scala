package Theorem.syntax

import Theorem.Monad

final class MonadOps[F[_], A](val self: F[A])(implicit val F: Monad[F]){
  def flatMap[B](fn: A => F[B]): F[B] = F.flatMap(self)(fn)
}

trait ToMonadOps {
  implicit def ToMonadOps[F[_], A](value: F[A])(implicit F0: Monad[F]): MonadOps[F, A] = new MonadOps[F, A](value)
}

trait MonadSyntax[F[_]] {
  def F: Monad[F]

  implicit def ToMonadOps[A](value: F[A]): MonadOps[F, A] = new MonadOps[F, A](value)(MonadSyntax.this.F)
}
