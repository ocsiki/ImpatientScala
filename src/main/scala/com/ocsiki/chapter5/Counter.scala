package com.ocsiki.chapter5

class Counter(var value: Int) {

//  private var value = 0 // You must initialize the field

  def increment() {
    if (value < Int.MaxValue) {
      print(value)
      value += 1
    }
    else {
     Int.MaxValue
    }
  } // Methods are public by default

  def current() = value

}