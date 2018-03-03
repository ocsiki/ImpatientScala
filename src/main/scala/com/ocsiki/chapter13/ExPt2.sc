/*
4. Write a function that receives a collection of strings and a map from strings to integers.
Return a collection of integers that are values of the map corresponding to one of the strings in the collection.
 For example, given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5),
 return Array(3, 5). Hint: Use flatMap to combine the Option values returned by get.
 */

def exercise4(names: Seq[String], attrib: Map[String, Int]): Iterable[Int] = {
  attrib.keys.filter(k => names.contains(k)).map(k => attrib(k))
}

exercise4(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5))

def exercise4variant(names: Iterable[String], attrib: Map[String, Int]): Iterable[Int] = {
  names.flatMap(name => attrib.get(name))
}

exercise4variant(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5))

//5. Implement a function that works just like mkString, using reduceLeft.
def mkStringByReducing(seq: Seq[Any]): String = {
  seq.reduceLeft(_.toString + _).toString
}

mkStringByReducing(Array(1, 2, "haha", 'c'))

/*
6. Given a list of integers lst, what is (lst :\ List[Int]())(_ :: _)? (List[Int]() /: lst)(_ :+ _)?
How can you modify one of them to reverse the list?
 */
def ex6var1(list: List[Int]) = {
  (list :\ List[Int]()) ((a, b) => b :+ a)
}

ex6var1(List(1, 2, 3, 4, 5))

def ex6var2(list: List[Int]) = {
  (List[Int]() /: list) ((a, b) => a :+ b)
}

ex6var2(List(1, 2, 3, 4, 5))

/*
7. In Section 13.10, “Zipping,” on page 187, the expression (prices zip quantities) map { p => p._1 * p._2 }
is a bit inelegant. We can’t do (prices zip quantities) map { _ * _ } because _ * _ is a function with two arguments,
and we need a function with one argument that is a tuple.
The tupled method of the Function object changes a function with two arguments to
one that takes a tuple. Apply tupled to the multiplication function so you can map it over the list of pairs.
 */
(List(1, 2, 3) zip List(10, 20, 30)) map { p => p._1 * p._2 }
(List(1, 2, 3) zip List(10, 20, 30)) map Function.tupled(_ * _)