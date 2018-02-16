package com.ocsiki.chapter5

import org.scalatest.{FlatSpec, Matchers}

class TimeSpec extends FlatSpec with Matchers {

  "A Time class" should "only be created with valid hours and minutes" in {

    val ex = intercept[Exception] {
      new Time(100,4)
    }

    ex shouldBe a [IllegalArgumentException]
    assert(ex.getMessage == "requirement failed: Please enter valid values for hours and minutes")
  }

  "the before method" should "return true if this time is before other time" in {
    val time = new Time(10, 5)
    val otherTime = new Time(10, 6)

    time.before(otherTime) shouldBe true
  }


  "A TimeInMinutes class" should "return before true if time is before other time" in {
    val minTime = new TimeInMinutes(10, 7)
    val otherMinTime = new TimeInMinutes(11, 0)


    minTime.before(otherMinTime) shouldBe true
  }

}
