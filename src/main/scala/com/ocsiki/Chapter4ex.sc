import scala.collection.{SortedMap, mutable}
import scala.collection.mutable.ArrayBuffer

//1. Set up a map of prices for a number of gizmos that you covet.
// Then produce a second map with the same keys and the prices at a 10 percent discount.
val gizmos = Map("phone" -> 100, "laptop" -> 500, "car" -> 9999)
def discoutGizmos(giz: Map[String, Int]) = {
  val discounts = for (value <- giz.values) yield value - value/10
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
def buildTreeOccurrenceMap(): java.util.TreeMap[String, Int] = {
  java.util.TreeMap[String, Int]
}


//6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
// and similarly for the other weekdays.
// Demonstrate that the elements are visited in insertion order.
def linkedWeekdayMap(): Unit = {

}
