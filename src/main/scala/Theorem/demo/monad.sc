import scalaz.{Reader, WriterT}
import scalaz.std.list._

val values = List(1, 2, 3, 4)

for {
  x <- values
  y <- values
} yield (x, y)

val valuesW = WriterT.put(values)(List.empty[String])

val writer: WriterT[List, List[String], Int] = for {
  x <- valuesW
  y <- valuesW
  _ <- WriterT.writerT(List((List(s"$x + $y = ${x + y}"), List.empty[Int])))
} yield x + y


val myReader = for {
  sum <- Reader((vals: List[Int]) => vals.sum)
} yield sum.toString


myReader.map(_.length)
  .flatMap(nr => Reader((values: List[Int]) => values.take(nr)))
  .run(List(20, 30, 1, 50))

