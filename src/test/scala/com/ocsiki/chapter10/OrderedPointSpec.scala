package com.ocsiki.chapter10

import org.scalatest.{FlatSpec, Matchers}

class OrderedPointSpec extends FlatSpec with Matchers{


  "Ordered points" should "sort correctly" in {
    val p1 = new OrderedPoint(4,5)
    val p2 = new OrderedPoint(4,7)

    p1.compare(p2) shouldBe -1
  }

  it should "sort correctly when points are equal" in {
    val p1 = new OrderedPoint(0,0)
    val p2 = new OrderedPoint(0,0)

    p1.compare(p2) shouldBe 0
  }

}
