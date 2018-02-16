package com.ocsiki.chapter9

object Exercise1 extends App {

  val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike

  println(egg)

  egg.translate(10, -10)

  println(egg)

  egg.grow(10, 20)

  println(egg)

}

trait RectangleLike {
  def getX: Double
  def getY: Double
  def getWidth: Double
  def getHeight: Double
  def setFrame(x: Double, y: Double, width: Double, height: Double)

  def translate(i: Double, j: Double): Unit = {
    setFrame(getX + i, getY + j, getWidth, getHeight)
  }

  def grow(i: Double, j: Double): Unit = {
      setFrame(getX, getY, getWidth + getX, getHeight + getY)
  }

  override def toString: String = "X: " + getX + " Y: " + getY + " Width: " + getWidth + " Height: " + getHeight
}


