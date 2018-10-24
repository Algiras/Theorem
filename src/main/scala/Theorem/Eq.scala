package Theorem

trait Eq[T] {
  def ===(left: T, right: T): Boolean

  def =/=(left: T, right: T): Boolean = ! ===(left, right)

  def equalIsNatural = false
}

object Eq {
  def apply[T](implicit T: Eq[T]): Eq[T] = T

  def equalA[A]: Eq[A] = new Eq[A] {
    override def ===(left: A, right: A): Boolean = left == right
    override def equalIsNatural: Boolean = true
  }

  def equalRef[A <: AnyRef]: Eq[A] = (left: A, right: A) => left eq right
  def equalBy[A, B: Eq](fn: A => B): Eq[A] = (left: A, right: A) => Eq[B].===(fn(left), fn(right))
}
