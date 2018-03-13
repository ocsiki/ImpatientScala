package com.ocsiki.chapter17

import java.util.concurrent.Executors

import org.scalatest.{AsyncFunSpec, Matchers}

import scala.concurrent.{ExecutionContext, Future}

class Chapter17Spec extends AsyncFunSpec with Matchers {

  private val concurrentExecutionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  describe("Exercise 01") {
    import com.ocsiki.chapter17.Exercises.Exercise1._

    it("should run firstFuture") {
      val futureSum = firstFuture()
      futureSum map { sum => sum shouldEqual 42 }
    }
  }

  describe("Exercise 02") {
    import com.ocsiki.chapter17.Exercises.Exercise2._

    it("should run doInOrder") {
      def func1(i: Int): Future[String] = Future(i.toString)
      def func2(s: String): Future[Char] = Future(s.charAt(0))
      val intToEventualChar: Int => Future[Char] = doInOrder(func1, func2)

      intToEventualChar(567).map(it => it shouldEqual '5')
    }
  }

  describe("Exercise 03") {
    import com.ocsiki.chapter17.Exercises.Exercise3._

    it("should run doInOrderSeq") {
      def f1(int: Int): Future[Int] = Future(int+10)
      def f2(int: Int): Future[Int] = Future(int + 20)
      def f3(int: Int): Future[Int] = Future(int + 30)

      val seq: Seq[Int => Future[Int]] = Seq(f1,f2,f3)

      val functionToIntToEventualInt = doInOrderMulti(seq)

      functionToIntToEventualInt(5).map(it => it shouldEqual 65)
    }
  }

  describe("Exercise 04") {
    import com.ocsiki.chapter17.Exercises.Exercise4._

    it("should run doTogether") {
      def func1(i: Int): Future[String] = Future(i.toString)
      def func2(i: Int): Future[Char] = Future((i*17).toString.charAt(0))

      val intToEventualTuple = doTogether(func1, func2)

      intToEventualTuple(567).map(it => it shouldEqual ("567", '9'))
    }
  }

  describe("Exercise 05") {
    import com.ocsiki.chapter17.Exercises.Exercise5._

    it("should run seqToFutureSeq") {
      def f1: Future[Int] = Future(5)
      def f2: Future[Int] = Future(6)

      seqToFutureSeq(Seq(f1,f2)).map(it => it shouldEqual Seq(5,6))
    }
  }

  describe("Exercise 06") {
    import com.ocsiki.chapter17.Exercises.Exercise6._

    it("should repeat until password is correct") {
      val inputs = scala.collection.mutable.Stack[String]("borp", "glorp", "wrong", "secret", "gorg")

      def readInput:String = {
        inputs.pop
      }

      def until(string: String): Boolean = {
        println("Checking " + string)
        Thread.sleep(1000)
        string.equalsIgnoreCase("secret")
      }

      val eventualString = repeat(readInput, until)

      eventualString.map(it => it shouldEqual "secret")
    }
  }

  describe("Exercise 07") {
    import com.ocsiki.chapter17.Exercises.Exercise7._

    it("should calculate the number of primes") {

      val eventualInt = parPrimeCount(BigInt(20))

      eventualInt.map(it => it shouldEqual 8)
    }
  }
}
