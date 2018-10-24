package Theorem

trait Semigroup[T] {
  def append(left: T, right: => T): T
}


object Semigroup {
  @inline def apply[T](implicit ev: Semigroup[T]): Semigroup[T] = ev
}