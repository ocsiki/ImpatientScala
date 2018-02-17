package com.ocsiki.chapter10

import java.awt.geom.Ellipse2D

import org.scalatest.{FlatSpec, Matchers}

class ShapePlaySpec extends FlatSpec with Matchers {

  "The translate method of ShapePlay" should "translate an ellipse" in {
    val egg = new Ellipse2D.Double(1, 2, 3, 4) with RectangleLike
    val transEgg = ShapePlay.translateEllipse(egg, 4, 5)

    transEgg.getX shouldBe 5
    transEgg.getY shouldBe 7

    transEgg.getWidth shouldBe 3
    transEgg.getHeight shouldBe 4
  }

}
