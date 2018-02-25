package com.ocsiki.chapter11

import org.scalatest.{FlatSpec, Matchers}

class FractionSpec extends FlatSpec with Matchers {

  "Fractions" should "be added correctly" in {
    val f1: Fraction = new Fraction(2,3)
    val f2: Fraction = new Fraction(4,5)

    f1 + f2 shouldBe new Fraction(22, 15)
  }

  it should "be multiplied correctly" in {
    val f1: Fraction = new Fraction(2,3)
    val f2: Fraction = new Fraction(4,5)

    f1 * f2 shouldBe new Fraction(8, 15)
  }

  it should "be divided correctly" in {
    val f1: Fraction = new Fraction(2,3)
    val f2: Fraction = new Fraction(4,5)

    f1 / f2 shouldBe new Fraction(10, 12)
  }

  it should "be subtracted correctly" in {
    val f1: Fraction = new Fraction(2,3)
    val f2: Fraction = new Fraction(4,5)

    f1 - f2 shouldBe new Fraction(-2, 15)
  }

  it should "reduce fraction after addition if possible" in {
    val f1: Fraction = new Fraction(2,9)
    val f2: Fraction = new Fraction(1,9)

    f1 + f2 shouldBe new Fraction(1,3)
  }


}
