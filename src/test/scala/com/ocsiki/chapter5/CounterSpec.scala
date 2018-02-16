package com.ocsiki.chapter5

import org.scalatest.{FlatSpec, Matchers}

class CounterSpec extends FlatSpec with Matchers {


  "A counter" should "increment itself by one" in {
    val counter: Counter = new Counter(1)

    assert({
      counter.increment()
      counter.current()
    } == 2)
  }

  it should "stop incrementing when max value is reached" in {
    val counter: Counter = new Counter(Integer.MAX_VALUE)

    assert({
      counter.increment()
      counter.current()
    } == Int.MaxValue)
  }



}
