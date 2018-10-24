package Theorem.laws

import Theorem.Applicative
import Theorem.syntax.applicative._

object ApplicativeLaws {
  def allApplicativeLaws[F[_]: Applicative, A, B](ls: F[A], v: A, fn: A => B) = {
    identityLaw(Applicative[F].pure(v)) && homomorphismLaw(v, fn) && interchangeLaw(v, fn)
  }

  def identityLaw[T[_]: Applicative, A](v: T[A]) = v.ap(v.pure(identity[A](_))) == v
  def homomorphismLaw[T[_]: Applicative, A, B](v: A, fn: A => B) = {
    val value: T[A] = Applicative[T].pure(v)

    value.ap(Applicative[T].pure(fn)) == Applicative[T].pure(fn(v))
  }

  def interchangeLaw[T[_]: Applicative, A, B](v: A, fn: A => B) = {
    val value: T[A] = Applicative[T].pure(v)
    val func: T[A => B] = Applicative[T].pure[A => B](fn)
    val func2 = Applicative[T].pure((fn: A => B) => {fn(v)})

    value.ap(func) == func.ap(func2)
  }
}
