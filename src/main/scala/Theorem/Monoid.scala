package Theorem

trait Monoid[F] extends Semigroup[F]{
  def zero: F
}

object Monoid {
  @inline def apply[F](implicit F: Monoid[F]): Monoid[F] = F

  def instance[A](fn: (A, A) => A, empty: A): Monoid[A] = new Monoid[A] {
    override def zero: A = empty

    override def append(left: A, right: => A): A = fn(left, right)
  }
}
