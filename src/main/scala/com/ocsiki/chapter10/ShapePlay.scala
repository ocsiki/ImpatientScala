package com.ocsiki.chapter10

object ShapePlay {

  def translateEllipse(egg: java.awt.geom.Ellipse2D with RectangleLike, i: Int, j: Int): java.awt.geom.Ellipse2D = {
    egg.translate(i, j)
    egg
  }

  def growEllipse(egg: java.awt.geom.Ellipse2D with RectangleLike, i: Int, j: Int): java.awt.geom.Ellipse2D = {
    egg.grow(i, j)
    egg
  }

}
