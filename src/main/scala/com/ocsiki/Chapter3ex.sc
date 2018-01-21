import scala.collection.mutable.ArrayBuffer
import scala.util.{Random, Sorting}
import java.awt.datatransfer._

import collection.JavaConverters._
import scala.collection.mutable

//1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
def populateArray(n: Int) = {
  val a = new Array[Int](n)
  for (i <- 0 until n) a(i) = Random.nextInt
  a
}

def populateSeq(n: Int) = {
  for (i <- 0 until n) yield Random.nextInt
}

val arr = populateArray(3)
var seq = populateSeq(10)

//2. Write a loop that swaps adjacent elements of an array of integers.
// For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
def swapy(ar: Array[Int]) = {
  for (i <- 0 until ar.length - 1; if i % 2 == 0) {
    val temp = ar(i + 1)
    ar(i + 1) = ar(i)
    ar(i) = temp
  }
  ar
}

print(swapy(Array(1, 2, 3, 4, 5)).mkString(" "))
print(swapy(Array(1, 2, 3, 4, 5, 6)).mkString(" "))

//3. Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield.
def swapyYield(ar: Array[Int]) = {
  for (i <- ar.indices) yield {
    if (i % 2 == 0)
      if (i + 1 == ar.length) ar(i)
      else ar(i + 1)
    else ar(i-1)
  }
}

print(swapyYield(Array(1, 2, 3, 4, 5)).mkString(" "))
print(swapyYield(Array(1, 2, 3, 4, 5, 6)).mkString(" "))


//4. Given an array of integers, produce a new array that contains all positive values of the original array,
// in their original order, followed by all values that are zero or negative, in their original order.
def splitPosNeg(ar: Array[Int]) = {
  (for (elem <- ar; if elem > 0) yield elem) ++ (for (elem <- ar; if elem <= 0) yield elem)
}

def splitPosNegV2(ar: Array[Int]) = {
  var pos: ArrayBuffer[Int] = ArrayBuffer[Int]()
  var neg: ArrayBuffer[Int] = ArrayBuffer[Int]()
  for (elem <- ar) yield {
    if (elem > 0) pos += elem
    else neg += elem
  }
  pos++neg
}

splitPosNeg(Array(1,-3,5,4,-10,-20,8))
splitPosNegV2(Array(1,-3,5,4,-10,-20,8))

//5. How do you compute the average of an Array[Double]?
def arrayAverage(a: Array[Double]) = {
  a.sum / a.length
}

print(arrayAverage(Array(5.1,6,7.9)))
print(arrayAverage(Array(1,2,3,4)))

//6. How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted order?
// How do you do the same with an ArrayBuffer[Int]?
def reverseSort(a: Array[Int]): Array[Int] = {
  Sorting.quickSort(a)
  for (i <- 0 until (a.length/2)) {
    val temp = a(i)
    a(i) = a(a.length - 1- i)
    a(a.length -1 -i) = temp
  }
  a
}

print(reverseSort(Array(4,1,3,4,8,10)).mkString(" "))

def reverseSort(a: ArrayBuffer[Int]) = {
  val ints = a.sortWith(_ > _)
  a.clear()
  a.appendAll(ints)
  a
}

print(reverseSort(ArrayBuffer(4,1,3,4,8,10)).mkString(" "))

//7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)
def newArrayWithDuplicatesRemoved(a: Array[Int]): Array[Int] = {
  val copySet = new ArrayBuffer[Int]
  for (elem <- a) {
    if (!copySet.contains(elem))
      copySet += elem
  }
  copySet.toArray
}

def removeDupes(a: Array[Int]) = {
  a.distinct
}

print(newArrayWithDuplicatesRemoved(Array(1,1,1,3,4,5,6,6,7)).mkString(" "))

//8. Suppose you are given an array buffer of integers and want to remove all but the first negative number.
def removeAllButFirstNegative(a: ArrayBuffer[Int]) = {
  val allNegativeIndices = new ArrayBuffer[Int]
  for (i <- a.indices; if a(i) < 0) allNegativeIndices += i
  val reverse = allNegativeIndices.drop(1).reverse
  println("reversed negs: " + reverse.mkString(" "))
  for (elem <- reverse)
    a.remove(elem)
  a
}

print(removeAllButFirstNegative(ArrayBuffer(1,2,3,-1,5,6,7,-3,-4)).mkString(" "))

//9. Improve the solution of the preceding exercise by collecting the positions that should be
// moved and their target positions. Make those moves and truncate the buffer.
// Don’t copy any elements before the first unwanted element.
def improvedRemoveAllButFirstNegative(a: ArrayBuffer[Int]) = {
  val allNegativeIndices = new ArrayBuffer[Int]
  for (i <- a.indices; if a(i) < 0) allNegativeIndices += i
}

//10. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America.
// Strip off the "America/" prefix and sort the result.
def getAmericanTimezones(): Array[String] = {
  val ds:Array[String] = java.util.TimeZone.getAvailableIDs
  val prefixes = for (elem <- ds; if elem.startsWith("America/")) yield elem.replace("America/", "")
  prefixes.sorted
}

print(getAmericanTimezones().mkString(" "))


//11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
//val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
//Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
// and get the return value as a Scala buffer.
// (Why this obscure class? It’s hard to find uses of java.util.List in the standard Java library.)
def dataTransfer(): mutable.Buffer[String] = {
  val flavors: SystemFlavorMap = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  val natives: mutable.Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor).asScala
  natives
}

print(dataTransfer().mkString(" "))

