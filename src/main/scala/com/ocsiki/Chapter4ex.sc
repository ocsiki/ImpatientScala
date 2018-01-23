import scala.collection.JavaConverters.{propertiesAsScalaMap,propertiesAsScalaMapConverter}
import scala.collection.mutable.ArrayBuffer
import scala.collection.{SortedMap, mutable}

//1. Set up a map of prices for a number of gizmos that you covet.
// Then produce a second map with the same keys and the prices at a 10 percent discount.
val gizmos = Map("phone" -> 100, "laptop" -> 500, "car" -> 9999)
def discoutGizmos(giz: Map[String, Int]) = {
  val discounts = for (value <- giz.values) yield value - value / 10
  giz.keys.zip(discounts).toMap
}

discoutGizmos(gizmos)

//2. Write a program that reads words from a file.
// Use a mutable map to count how often each word appears.
def readFileJava(): java.util.Scanner = {
  new java.util.Scanner(new java.io.File("D:\\myfile.txt"))
}

def buildOccurrenceMap(): scala.collection.mutable.Map[String, Int] = {
  val scanner = readFileJava()
  val wordCountPair = scala.collection.mutable.Map[String, Int]()
  while (scanner.hasNext()) {
    val word = scanner.next()
    if (wordCountPair.keySet.contains(word))
      wordCountPair += (word -> (wordCountPair(word) + 1))
    else
      wordCountPair += (word -> 1)
  }
  wordCountPair
}

print(buildOccurrenceMap())

//3.  Repeat the preceding exercise with an immutable map.
def buildImmutableOccurrenceMap(): Map[String, Int] = {
  val scanner = readFileJava()
  val words = ArrayBuffer[String]()
  while (scanner.hasNext()) {
    words += scanner.next
  }

  val wordSet = words.toSet
  val occurs = ArrayBuffer[Int]()

  for (elem <- wordSet) {
    occurs += words.count(x => x == elem)
  }

  (wordSet zip occurs).toMap
}

print(buildImmutableOccurrenceMap())

//4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.
def buildSortedOccurrenceMap(): SortedMap[String, Int] = {
  SortedMap[String, Int]() ++ buildOccurrenceMap()
}

print(buildSortedOccurrenceMap())

//5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.
//def buildTreeOccurrenceMap(): java.util.TreeMap[String, Int] = {
//  java.util.TreeMap[String, Int]
//}


//6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
// and similarly for the other weekdays.
// Demonstrate that the elements are visited in insertion order.
def linkedWeekdayMap(): mutable.LinkedHashMap[String, Int] = {
  var weekdays = mutable.LinkedHashMap[String, Int]("Monday" -> java.util.Calendar.MONDAY)
  weekdays += ("Tuesday" -> java.util.Calendar.TUESDAY, "Wednesday" -> java.util.Calendar.WEDNESDAY,
    "Thursday" -> java.util.Calendar.THURSDAY, "Friday" -> java.util.Calendar.FRIDAY,
    "Saturday" -> java.util.Calendar.SATURDAY, "Sunday" -> java.util.Calendar.SUNDAY)

  weekdays
}

print(linkedWeekdayMap())

//7. Print a table of all Java properties reported by
// the getProperties method of the java.lang.System class
def printPropertiesTable(): Unit = {

  val properties: mutable.Map[String, String] = java.lang.System.getProperties.asScala
  val longestString = properties.keySet.maxBy(_.length)

  for (elem <- properties) {
    println(elem._1 + " "*(longestString.length - elem._1.length) + "|" + elem._2)
  }
}

printPropertiesTable()

//8. Write a function minmax(values: Array[Int])
// that returns a pair containing the smallest and the largest values in the array.
def minmax(values: Array[Int]): Tuple2[Int, Int] = {
  Tuple2(values.min, values.max)
}

minmax(Array(10,2,3,4,5,60,7,7,8,9))

//9. Write a function lteqgt(values: Array[Int], v: Int)
// that returns a triple containing the counts
// of values less than v, equal to v, and greater than v.
def lteqgt(values: Array[Int], v: Int): Tuple3[Int, Int, Int] = {
  val less = (for (elem <- values; if elem < v) yield elem).length
  val eq = (for (elem <- values; if elem == v) yield elem).length
  val greater = (for (elem <- values; if elem > v) yield elem).length

  Tuple3(less, eq, greater)
}

lteqgt(Array(1,2,3,4,5,6,6,7,8,10), 6)

//10. What happens when you zip together two strings, such as "Hello".zip("World")?
// Come up with a plausible use case.
def zippy(s1: String, s2: String) = {
  s1.zip(s2)
}
zippy("Hello", "World")
//build array of pairs