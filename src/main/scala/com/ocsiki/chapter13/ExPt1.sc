/*
1. Write a function that, given a string, produces a map of the indexes of all characters.
For example, indexes("Mississippi") should return a map associating 'M' with the set {0},
'i' with the set {1, 4, 7, 10}, and so on. Use a mutable map of characters to mutable sets.
How can you ensure that the set is sorted?
 */
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
def indexes(s: String) = {
  s.indices.foldLeft(mutable.Map[Char, mutable.SortedSet[Int]]()) {
    (m, i) => m += (s(i) -> (m.getOrElse(s(i), mutable.SortedSet[Int]()) += i))
  }
}

indexes("Mississippi")
indexes("Banana")

/*
2. Repeat the preceding exercise, using an immutable map of characters to lists.
 */
import scala.collection.immutable
def indexesImmutable(s: String) = {
  s.indices.foldLeft(immutable.Map[Char, immutable.List[Int]]()) {
    (m, i) => m + (s(i) -> (m.getOrElse(s(i), immutable.List[Int]()) ::: immutable.List(i)))
  }
}

indexesImmutable("Mississippi")
indexesImmutable("Banana")

/*
3. Write a function that removes every second element from a ListBuffer.
Try it two ways. Call remove(i) for all even i starting at the end of the list.
Copy every second element to a new list. Compare the performance.
 */
def removeSecond1(lb: ListBuffer[Int]) = {
  for (i <- lb.indices.reverse if i % 2 == 0) lb.remove(i)
  lb
}

def removeSecond2(lb: ListBuffer[Int]) = {
  val newlb = ListBuffer[Int]()
  for (i <- lb.indices if i % 2 == 0) newlb += lb(i)
  newlb
}

def benchmark(orderOrMagnitude: Int, runTimes: Int, f: ListBuffer[Int] => ListBuffer[Int]): Long = {

  var times = ListBuffer[Long]()
  val formatter = java.text.NumberFormat.getIntegerInstance

  for (i <- 0 to runTimes) {
    val bigList: ListBuffer[Int] = (1 to (1 * orderOrMagnitude)).to[ListBuffer]
    val timerStart1 = java.time.Instant.now().toEpochMilli
//    println(formatter.format(timerStart1))
    val l1 = f(bigList)
    val timerEnd1 = java.time.Instant.now().toEpochMilli
//    println(formatter.format(timerEnd1))
    val duration = timerEnd1 - timerStart1
    println("Run duration was: " + formatter.format(duration))
    times += duration
  }

  val aveageDuration = times.sum / times.size
  println("Average duration was: " + formatter.format(aveageDuration))
  aveageDuration
}

val b1 = benchmark(100000, 10, removeSecond1)
val b2 = benchmark(100000, 10, removeSecond2)
if (b1 < b2) println("First algo won")
else if (b1 == b2) print("Tie")
else println("Second algo won")