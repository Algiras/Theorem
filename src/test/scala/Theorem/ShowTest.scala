package Theorem

import org.specs2._
import Theorem.syntax.show._
import Theorem.std.string._
import Theorem.std.int._
import Theorem.std.list._

class ShowTest extends mutable.Specification{

  "Show" should {
    "Examples" should {
      "provide a version for basic types" >> {

        "String".show must_=== "String"
        12.show must_=== "12"
        List(1, 2, 3).show must_=== "[1,2,3]"
      }
    }
  }
}
