import scala.collection.mutable

/*
8. Write a function that turns an array of Double values into a two-dimensional array.
Pass the number of columns as a parameter. For example, with Array(1, 2, 3, 4, 5, 6) and three columns,
return Array(Array(1, 2, 3), Array(4, 5, 6)). Use the grouped method.
 */
def columnize(array: Array[Double], int: Int): Array[Array[Double]] = {
  array.grouped(int).toArray
}

columnize(Array(1, 2, 3, 4, 5, 6), 3).deep

/*
9. The Scala compiler transforms a for/yield expression
for (i <- 1 to 10; j <- 1 to i) yield i * j
to invocations of flatMap and map, like this:
(1 to 10).flatMap(i => (1 to i).map(j => i * j))
Explain the use of flatMap. Hint: What is (1 to i).map(j => i * j) when i is 1, 2, 3?
What happens when there are three generators in the for/yield expression?
 */
(1 to 10).map(i => (1 to i).map(j => i * j))


(1 to 10).flatMap(i => (1 to i).flatMap(j => (1 to j).map(k => k *j * i)))

/*
10. The method java.util.TimeZone.getAvailableIDs yields time zones such as Africa/Cairo and Asia/Chungking.
Which continent has the most time zones? Hint: groupBy.
 */
val tzids = java.util.TimeZone.getAvailableIDs
tzids.filter(_.contains("/")).groupBy(tz => tz.substring(0, tz.indexOf("/"))).maxBy(_._2.length)

/*
11. Harry Hacker reads a file into a string and wants to use a parallel collection to update the letter
frequencies concurrently on portions of the string. He uses the following code:
val frequencies = new scala.collection.mutable.HashMap[Char, Int]
for (c <- str.par) frequencies(c) = frequencies.getOrElse(c, 0) + 1
Why is this a terrible idea? How can he really parallelize the computation? (Hint: Use aggregate.)
 */
val str = "Harry Hacker reads a file into a string and wants to use a parallel collection to update the letter"

//str.par.aggregate(mutable.HashMap[Char, Int]())((m, c) => m + (c -> (m.getOrElse(c, 0) + 1)), ()

