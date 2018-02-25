package com.ocsiki.chapter11

import scala.annotation.tailrec

/*
3. Implement the Fraction class with operations + - * /.
Normalize fractions, for example, turning 15/–6 into –5/2.
 Divide by the greatest common divisor
 */

class Fraction(e: Int, d: Int) {


  private val enum: Int = if (d == 0) 1 else e * sign(d) / gcd(e,d)

  private val denom: Int = if (d == 0) 0 else scala.math.abs(d) / gcd(e, d)

  override def toString: String = s"$enum/$denom"

  private def sign(i: Int) =  if (i < 0) -1 else if (i>0) 1 else 0

  @tailrec
  private def gcd(i: Int, j: Int): Int = {
    if (j == 0) scala.math.abs(i)
    else gcd(j, i % j)
  }

  def +(other: Fraction): Fraction = {
    if (denom == other.denom) new Fraction(enum + other.enum, denom)
    else new Fraction(enum * other.denom + other.enum * denom, denom * other.denom)
  }

  def *(other: Fraction): Fraction = {
    new Fraction(enum * other.enum, denom * other.denom)
  }

  def -(other: Fraction): Fraction = {
    if (denom == other.denom) new Fraction(enum - other.enum, denom)
    else new Fraction(enum *other.denom - other.enum * denom, denom * other.denom)
  }

  def /(other: Fraction): Fraction = {
    new Fraction(enum * other.denom, denom * other.enum)
  }

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Fraction]) false

    val other = obj.asInstanceOf[Fraction]

    if (enum == other.enum && denom == other.denom) true
    else false
  }


}
