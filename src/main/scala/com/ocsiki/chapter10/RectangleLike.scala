package com.ocsiki.chapter10

trait RectangleLike extends java.awt.geom.Ellipse2D {

  def translate(i: Double, j: Double) = {
    setFrame(getX + i, getY + j, getWidth, getHeight)
  }

  def grow(i: Double, j: Double) = {
    assume(i >= 0 && j >= 0)

    setFrame(getX, getY, getWidth + i, getHeight + j)

  }

}
