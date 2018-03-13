package com.ocsiki.chapter17

import scala.collection.immutable
import scala.collection.immutable.NumericRange
import scala.concurrent.Future

object Exercises {

  import scala.concurrent.ExecutionContext.Implicits.global

  object Exercise1 {
    def firstFuture(): Future[Int] = {
      for (n1 <- Future {
        Thread.sleep(1000); 2
      }; n2 <- Future {
        Thread.sleep(1000); 40
      }) yield n1 + n2
    }
  }

  object Exercise2 {
    /*
    2. Write a function doInOrder that, given two functions f: T => Future[U] and g: U => Future[V],
    produces a function T => Future[U] that, for a given t, eventually yields g(f(t))
     */
    def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = {
      f.andThen(it => it.flatMap(i => g(i)))
    }
  }

  object Exercise3 {
    /*
    3. Repeat the preceding exercise for any sequence of functions of type T => Future[T].
     */
    def doInOrderMulti[T](seqF: Seq[T => Future[T]]): T => Future[T] = {
      seqF.reduceLeft((a, b) => a.andThen(it => it.flatMap(j => b(j))))
    }
  }

  object Exercise4 {
    /*
    4. Write a function doTogether that, given two functions f: T => Future[U] and g: U => Future[V],
    produces a function T => Future[(U, V)], running the two computations in parallel and,
    for a given t, eventually yielding (f(t), g(t)).
     */
    def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = {
      t: T => {
        f(t).flatMap(i => g(t).map(j => (i, j)))
      }
    }
  }

  object Exercise5 {
    /*
    5. Write a function that receives a sequence of futures and returns a future that eventually
    yields a sequence of all results.
     */
    def seqToFutureSeq[T](seq: Seq[Future[T]]): Future[Seq[T]] = {
      Future.sequence(seq)
    }
  }

  object Exercise6 {
    /*
    6. Write a method Future[T] repeat(action: => T, until: T => Boolean) that asynchronously repeats the action
    until it produces a value that is accepted by the until predicate, which should also run asynchronously.
    Test with a function that reads a password from the console, and a function that simulates a validity check
    by sleeping for a second and then checking that the password is "secret". Hint: Use recursion.
     */
    @scala.annotation.tailrec
    def repeat[T](action: => T, until: T => Boolean): Future[T] = {
      val result = action
      if (until(result)) Future(result)
      else repeat(action, until)
    }
  }

  object Exercise7 {
    /*
    7. Write a program that counts the prime numbers between 1 and n, as reported by BigInt.isProbablePrime.
    Divide the interval into p parts, where p is the number of available processors.
    Count the primes in each part in concurrent futures and combine the results.
     */
    def parPrimeCount(n: BigInt): Future[Int] = {

      def eventualPrimesInPart(range: NumericRange.Inclusive[BigInt]): Future[Int] = {
        Future(range.count(it => it.isProbablePrime(100)))
      }

      val p = Runtime.getRuntime.availableProcessors()

      def splitInterval(range: NumericRange.Inclusive[BigInt]): immutable.Seq[NumericRange.Inclusive[BigInt]] = {
        val chunkSize = scala.math.max(range.length / p, 1)
        val starts = range.by(chunkSize).take(p)
        val ends = starts.map(_ - 1).drop(1) :+ range.end
        starts.zip(ends).map(it => it._1 to it._2)
      }

      val futures: immutable.Seq[Future[Int]] = for {
        range <- splitInterval(BigInt(0) to n)
      } yield eventualPrimesInPart(range)

      val eventualInts: Future[immutable.Seq[Int]] = Future.sequence(futures)


      eventualInts.map(_.sum)
    }

  }

  object Exercise8 {
    /*
    8. Write a program that asks the user for a URL, reads the web page at that URL,
     and displays all the hyperlinks. Use a separate Future for each of these three steps.
     */

  }

}
