package com.ocsiki.chapter5

import org.scalatest.{FlatSpec, Matchers}

class StudentSpec extends FlatSpec with Matchers {

  "Student class" should "be able to be created and then modified" in {
    val stud = new Student("Ted", 1)

    stud.name shouldBe "Ted"

    stud.name = "Vaz"

    stud.name shouldBe "Vaz"
  }

}
