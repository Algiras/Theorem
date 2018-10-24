package Theorem

import scala.language.higherKinds

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(fn: A => B): F[B]
}

object Functor {
  @inline def apply[F[_]](implicit F: Functor[F]): Functor[F] = F
}
