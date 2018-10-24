import Theorem.std.string._
import Theorem.std.int._
import Theorem.std.list._
import Theorem.syntax.show._
import Theorem.Show

case class Friend(name: String, age: Int, friends: List[Friend])

implicit val showFriend: Show[Friend] = new Show[Friend]{ self =>

  override def show(value: Friend) =
    s"Friend(name=${value.name.show}, age=${value.age.show}, friends=${value.friends.map(self.show).show}"
}

val b = Friend("Bartas", 27, friends = List())
val a = Friend("Algimantas", 27, friends = List(b))

a.show
