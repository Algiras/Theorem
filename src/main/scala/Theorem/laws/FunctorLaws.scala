package Theorem.laws

import Theorem.Functor
import Theorem.syntax.functor._


object FunctorLaws {
  def allFunctorLaws[F[_]: Functor, A, B, C](v: F[A], fn1: A => B, fn2: B => C) = identityLaw(v) && compositionLaw(v, fn1, fn2)

  def identityLaw[F[_]: Functor, A](v: F[A]): Boolean = v.map(identity[A]) == identity(v)
  def compositionLaw[F[_]: Functor, A, B, C](v: F[A], fn1: A => B, fn2: B => C): Boolean = {
    v.map(fn1 andThen fn2) == v.map(fn1).map(fn2)
  }
}
