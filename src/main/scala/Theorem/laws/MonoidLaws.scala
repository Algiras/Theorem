package Theorem.laws

import Theorem.Monoid
import Theorem.syntax.monoid._

object MonoidLaws {
    def allMonoidLaws[T: Monoid](x: T, y: T, z: T) = leftIdentityLaw[T](x) && rightIdentityLaw[T](x) && associativityLaw[T](x, y, z)

    def leftIdentityLaw[T: Monoid](x: T) = x.mappend(Monoid[T].zero) == x
    def rightIdentityLaw[T: Monoid](x: T) = Monoid[T].zero.mappend(x) == x
    def associativityLaw[T: Monoid](x: T, y: T, z: T) = SemigroupLaws.associativityLaw[T](x, y, z)
}
