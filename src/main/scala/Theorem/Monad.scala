package Theorem

import scala.language.higherKinds

trait Monad[F[_]] extends Applicative[F]{
  override def map[A, B](fa: F[A])(fn: A => B): F[B] = flatMap[A, B](fa)(value => pure(fn(value)))

  def flatMap[A, B](value: F[A])(fn: A => F[B]): F[B]
}

object Monad {
  @inline def apply[F[_]](implicit F: Monad[F]): Monad[F] = F
}
