package Theorem.laws

import Theorem.Semigroup
import Theorem.syntax.semigroup._

object SemigroupLaws {
    def associativityLaw[T: Semigroup](x: T, y: T, z: T): Boolean = ((x |+| y) |+| z) == (x |+| (y |+| z))
}
