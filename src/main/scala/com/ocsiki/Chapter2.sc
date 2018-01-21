import scala.collection.mutable.ArrayBuffer
//signum

def getSignum(i: Int): Int = {

  if (i < 0) -1
  else if (i == 0) 0
  else 1
}

//value of empty block is (), type is Unit
{}

var y = 2
def x = y = 1

do {
  y+=1

} while (y < 10)


val a = ArrayBuffer(2, -3, 5, -7, 11)

val positionsToKeep = for (i <- a.indices if a(i) >= 0) yield i

for (j <- positionsToKeep.indices) a(j) = a(positionsToKeep(j))

a.trimEnd(a.length - positionsToKeep.length)

println(a.toString)