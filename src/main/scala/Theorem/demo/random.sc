import scalaz.IndexedReaderWriterStateT
import scalaz.std.list._
import scalaz.std.option._

import scala.util.Random

type CS[T] = IndexedReaderWriterStateT[Option, Int, List[String], Random, Random, T]

def CS[T] = IndexedReaderWriterStateT[Option, Int, List[String], Random, Random, T] _

val MD = IndexedReaderWriterStateT.rwstMonad[Option, Int, List[String], Random]

val randomInt: CS[Int] = IndexedReaderWriterStateT((_, s) => Option((List.empty[String], s.nextInt(), s)))

val randomDouble = for {
  state <- MD.get
} yield state.nextDouble()

val randomPositiveInt: CS[Int] = for {
  size <- MD.ask
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
  nr <- randomPositiveInt
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

def randomPerson = for {
  name <- randomString
  _ <- MD.tell(List("message"))
  balance <- randomInt
} yield Person(name, balance)

val a = randomList(randomPair(randomInt, randomInt)).run(0, new Random(1L))
val b = randomList(randomBool).run(0, new Random(1L))


val c = randomList(randomPositiveInt).run(0, new Random(1L))

val d = randomPerson.run(12, new Random(1L))


