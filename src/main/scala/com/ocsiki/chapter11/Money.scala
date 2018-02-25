package com.ocsiki.chapter11

/*
4. Implement a class Money with fields for dollars and cents.
Supply +, - operators as well as comparison operators == and <.
For example, Money(1, 75) + Money(0, 50) == Money(2, 25) should be true.
Should you also supply * and / operators? Why or why not?
 */

class Money(d: Int, c: Int) {

  override def toString: String = s"\$$d.$c"

  override def equals(obj: scala.Any): Boolean = {
    if (obj.isInstanceOf[Money]) {
      val other = obj.asInstanceOf[Money]
      if (d == other.d && c == other.c) true
      else false
    } else false
  }

}
