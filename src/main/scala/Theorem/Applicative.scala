package Theorem

trait Applicative[F[_]] extends Functor[F]{
  def pure[A](value: A): F[A]
  def ap[A, B](fn: F[A => B])(f: F[A]): F[B]
  override def map[A, B](fa: F[A])(fn: A => B): F[B] = ap(pure(fn))(fa)
}


object Applicative {
  @inline def apply[F[_]](implicit F: Applicative[F]): Applicative[F] = F
}