/*
1. Write a function values(fun: (Int) => Int, low: Int, high: Int)
that yields a collection of function inputs and outputs in a given range.
For example, values(x => x * x, -5, 5)
should produce a collection of pairs (-5, 25), (-4, 16), (-3, 9), . . . , (5, 25).
 */

def values(fun: (Int) => Int, low: Int, high: Int) = {
  for (i <- low to high) yield (i, fun(i))
}

values(x => x * x, -5, 5)

def valuesMap(fun: (Int) => Int, low: Int, high: Int) = {
  (low to high).map(it => fun(it)).zip(low to high)
}

valuesMap(x => x * x, -5, 5)

/*
2. How do you get the largest element of an array with reduceLeft?
 */

val a1 = Array(1,2,3,4,8,4,5,9,1,2)

a1.reduceLeft(_ max _)

/*
3. Implement the factorial function using to and reduceLeft, without a loop or recursion.
 */

def factorial(i: Int) = {
  if (i < 1) 1
  else (1 to i).reduceLeft(_ * _)
}

factorial(5)

/*
4. The previous implementation needed a special case when n < 1.
Show how you can avoid this with foldLeft.
(Look at the Scaladoc for foldLeft.
It’s like reduceLeft, except that the first value in the chain of combined values
is supplied in the call.)
 */

def factorialSmart(i: Int) = (1 to i).foldLeft(1)((_ * _))

factorialSmart(5)

/*
5. Write a function largest(fun: (Int) => Int, inputs: Seq[Int]) that yields the largest value
of a function within a given sequence of inputs. For example, largest(x => 10 * x - x * x, 1 to 10)
should return 25. Don’t use a loop or recursion.
*/

def largest(fun: (Int) => Int, inputs: Seq[Int]) = {
  inputs.map(fun).max
}

largest(x => 10 * x - x * x, 1 to 10)

/*
6. Modify the previous function to return the input at which the output is largest.
For example, largestAt(x => 10 * x - x * x, 1 to 10) should return 5. Don’t use a loop or recursion.
 */

def largestIndex(fun: (Int) => Int, inputs: Seq[Int]) = {
  inputs.map(fun).zip(inputs).max._2
}

largestIndex(x => 10 * x - x * x, 1 to 10)

/*
7. The function _ + _ takes two Int parameters, not an (Int, Int) pair.
Write a function adjustToPair that receives a function of type (Int, Int) => Int
and returns the equivalent function that operates on a pair. For example, adjustToPair(_ * _)((6, 7)) is 42.

Then use this function in conjunction with map to compute the sums of the elements in pairs.
 */

def adjustToPair(fun: (Int, Int) => Int)(i: (Int,Int)) = {
  fun(i._1, i._2)
}

adjustToPair(_ * _)(6, 7)
val pairs = (1 to 10) zip (11 to 20)
pairs.map(adjustToPair(_ + _))

/*
8. In Section 12.8, “Currying,” on page 164, you saw the corresponds method used with two arrays of strings.
Make a call to corresponds that checks whether the elements in an array of strings
have the lengths given in an array of integers.
 */

/*
val a = Array("Hello", "World")

val b = Array("hello", "world")

a.corresponds(b)(_.equalsIgnoreCase(_))

def corresponds[B](that: Seq[B])(p: (A, B) => Boolean): Boolean
 */

val a = Array("Hello", "World")
a.corresponds(Array(4,5))((a, b) => a.size == b)
a.corresponds(Array(5,5))((a, b) => a.size == b)

/*
9. Implement corresponds without currying.
Then try the call from the preceding exercise. What problem do you encounter?
 */

//def corresponds[A, B](it: Seq[A], that: Seq[B], fun: (A, B) => Boolean): Boolean = {
//  fun(it, that)
//}

// corresponds(Array("Hello", "World"), Array(4,5), (a,b) => a.size == b) - can't infer types

/*
10. Implement an unless control abstraction that works just like if, but with an inverted condition.
Does the first parameter need to be a call-by-name parameter? Do you need currying?
 */

def unless(cond: => Boolean)(block: => Unit) = {

  if (!cond)
    block

}

val i = 5
unless(i == 6)(print("Unless"))