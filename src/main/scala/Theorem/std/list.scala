package Theorem.std

import Theorem._

trait ListInstances0 {
  implicit def listEq[A](implicit A0: Eq[A]): Eq[List[A]] = new ListEq[A] {
    override implicit def A = A0
  }
}

trait ListInstances extends ListInstances0 {
  implicit object ListInstances extends Functor[List] with Applicative[List] with Monad[List]{
    override def pure[A](value: A): List[A] = List(value)

    override def ap[A, B](fn: List[A => B])(f: List[A]): List[B] = for {
      value <- f
      func <- fn
    } yield func(value)

    override def flatMap[A, B](value: List[A])(fn: A => List[B]): List[B] = value.map(fn(_)).flatten
  }

  implicit def listMonoid[A]: Monoid[List[A]] = Monoid.instance[List[A]](
    (a: List[A], b: List[A]) => List.concat(a, b),
    List.empty[A]
  )

  implicit def listShow[A](implicit A: Show[A]): Show[List[A]] = Show.show { as =>
    val content = as.map(v => A.show(v)).mkString(", ")
    s"[$content]"
  }
}

private trait ListEq[A] extends Eq[List[A]] {
  implicit def A : Eq[A]

  override def equalIsNatural: Boolean = A.equalIsNatural

  override def ===(left: List[A], right: List[A]): Boolean = (left corresponds right)(Eq[A].===)
}

object list extends ListInstances {}