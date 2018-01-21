val ragged = Array.ofDim[Int](3,3)

ragged(1) = Array[Int](4)

val a = Array("Mary", "a", "had", "lamb", "little")

//java.util.Arrays.binarySearch(a, "beef") // Does not work

java.util.Arrays.binarySearch(a.asInstanceOf[Array[Object]], "beef")

import scala.collection.JavaConversions.bufferAsJavaList

import scala.collection.mutable.ArrayBuffer

val command = ArrayBuffer("ls", "-al", "/home/cay")

val pb = new ProcessBuilder(command) // Scala to Java