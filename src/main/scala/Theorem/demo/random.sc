import scalaz.StateT
import scalaz.std.option._

import scala.util.Random

type CS[T] = StateT[Option, Random, T]

def CS[T] = StateT[Option, Random, T](_)

val MD = StateT.stateTMonadState[Random, Option]

val randomInt: CS[Int] = StateT((v: Random) => Option(v, v.nextInt()))

val randomDouble = for {
  state <- MD.get
} yield state.nextDouble()

def randomPositiveInt(size: Int): CS[Int] = for {
  nr <- randomDouble
} yield (nr * size).toInt

val randomBool: CS[Boolean] = for {
  state <- MD.get
} yield state.nextBoolean()

def randomPair[A, B](left: CS[A], right: CS[B]): CS[(A, B)] = {
  for {
    l <- left
    r <- right
  } yield (l, r)
}

val randomString: CS[String] = for {
  nr <- randomPositiveInt(100)
  state <- MD.get
} yield state.nextString(nr)

def randomList[T](pairGenerator: CS[T]): CS[List[T]] = {
  for {
    nr <- pairGenerator
    continue <- randomBool
    state <- MD.get
    rest <- if(continue) randomList(pairGenerator) else MD.constantState(Nil, state)
  } yield nr :: rest
}

case class Person(name: String, balance: Int)

def randomPerson: CS[Person] = for {
  name <- randomString
  balance <- randomInt
} yield Person(name, balance)

val a = randomList(randomPair(randomInt, randomInt)).eval(new Random(1L))
val b = randomList(randomBool).eval(new Random(1L))


val c = randomList(randomPositiveInt(10)).eval(new Random(1L))

val d = randomList(randomPerson).eval(new Random(1L))


