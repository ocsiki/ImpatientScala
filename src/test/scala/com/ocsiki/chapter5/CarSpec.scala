package com.ocsiki.chapter5

import org.scalatest.{FlatSpec, Matchers}

class CarSpec extends FlatSpec with Matchers {

  "The class" should "allow construction with various argument list sizes" in {
    val c1: Car = new Car("Ford", "Mustang")

    c1.licencePlate shouldBe ""
    c1.modelYear shouldBe -1
  }

}
