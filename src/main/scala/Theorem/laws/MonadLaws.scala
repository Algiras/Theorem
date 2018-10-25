package Theorem.laws

import Theorem.Monad
import Theorem.syntax.monad._

object MonadLaws {
  def allMonadLaws[F[_]: Monad, A, B, C](value: A, fn1: A => F[B], fn2: B => F[C]): Boolean = {
    val pureValue = Monad[F].pure(value)
    leftIdentity(value, fn1) && rightIdentity(pureValue) && associativity(pureValue, fn1, fn2)
  }

  def leftIdentity[F[_]: Monad, A, B](value: A, fn: A => F[B]): Boolean = {
    val cValue = Monad[F].pure(value)

    cValue.flatMap(fn) == fn(value)
  }

  def rightIdentity[F[_]: Monad, A](value: F[A]): Boolean = {
    value.flatMap(Monad[F].pure(_)) == value
  }

  def associativity[F[_]: Monad, A, B, C](value: F[A], f1: A => F[B], f2: B => F[C]): Boolean = {
    def fn3(v: A): F[C] = f1(v).flatMap(f2)
    value.flatMap(f1).flatMap(f2) == value.flatMap(fn3)
  }
}
