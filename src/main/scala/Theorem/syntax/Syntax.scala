package Theorem.syntax

trait Syntax {
  object eq extends ToEqOps
  object functor extends ToFunctorOps
  object semigroup extends ToSemigroupOps
  object monoid extends ToMonoidOps
  object show extends ToShowOps
  object applicative extends ToApplicativeOps
  object monad extends ToMonadOps
}
