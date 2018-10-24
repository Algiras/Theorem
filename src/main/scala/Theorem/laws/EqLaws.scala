package Theorem.laws

import Theorem.Eq
import Theorem.syntax.eq._

object EqLaws {
  def allEqLaws[T: Eq, E: Eq](a: T, b: T, c: T, fn: T => E): Boolean = reflexivityLaw(a) &&
    symmetryLaw(a, b) &&
    transitivityLaw(a, b, c) &&
    substitutivityLaw(a, b, fn) &&
    negationLaw(a, b) &&
    naturality(a, b)

  def reflexivityLaw[T: Eq](value: T): Boolean = value === value

  def symmetryLaw[T: Eq](a: T, b: T): Boolean = (a === b) == (b === a)

  def transitivityLaw[T: Eq](a: T, b: T, c: T): Boolean = (a === b) && (b === c) && (a === c)

  def substitutivityLaw[T: Eq, E: Eq](a: T, b: T, fn: T => E): Boolean = (a === b) && (fn(a) === fn(b))

  def negationLaw[T: Eq](a: T, b: T): Boolean = (a =/= b) == !(a === b)

  def naturality[T: Eq](a: T, b: T): Boolean = Eq[T].equalIsNatural == ((a === b) == (a == b))
}
