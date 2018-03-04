/*
2. Using pattern matching, write a function swap that receives a pair of
integers and returns the pair with the components swapped.
 */
def swap(p: (Int, Int)): (Int, Int) = {
  p match {
    case (a, b) => (b, a)
  }
}

swap((1, 2))

/*
3. Using pattern matching, write a function swap that swaps the first two elements of an array
provided its length is at least two.
 */
def swapAr(array: Array[Any]): Array[Any] = {
  array match {
    case a: Array[Any] => a
    case Array(a) => Array(a)
    case Array(a, b) => Array(b, a)
    case Array(a, b, c@_*) => Array(b, a) ++ c
  }
}
swapAr(Array(1, 2, 3))
swapAr(Array(1))
swapAr(Array())
swapAr(Array(1, 2))

/*
5. Write a leafSum function to compute the sum of all elements in the leaves,
using pattern matching to differentiate between numbers and lists.
 */
def leafSum(list: List[Any]): Int = {
  sumAcc(0, list)
}

def sumAcc(acc: Int, list: Seq[Any]): Int = {
  list match {
    case List(a: Int, tail@_*) => sumAcc(a + acc, tail)
    case List(a: List[Any]) => sumAcc(acc, a)
    case List(a: List[Any], tail@_*) => sumAcc(acc, a) + sumAcc(acc, tail)
    case List() => acc
  }
}
val list = List(List(3, 8), 2, List(5))
leafSum(list)
var list2 = List(1, 2, 3)
leafSum(list2)

def leafSumV2(list: List[Any]): Int = {
  list.map {
    case a: Int => a
    case a: List[Any] => leafSumV2(a)
    case _ => 0
  }.sum
}

leafSumV2(list)
leafSumV2(list2)

/*
6. A better way of modeling such trees is with case classes. Let’s start with binary trees.
Write a function to compute the sum of all elements in the leaves.
 */

sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

def sumBinTree(binaryTree: BinaryTree): Int = {
  binaryTree match {
    case l: Leaf => l.value
    case n: Node => sumBinTree(n.left) + sumBinTree(n.right)
    case _ => 0
  }
}

val b1: BinaryTree = Node(Leaf(3), Node(Leaf(4), Node(Leaf(1), Leaf(2))))

sumBinTree(b1)

/*
7. Extend the tree in the preceding exercise so that each node can
have an arbitrary number of children, and reimplement the leafSum function.
The tree in Exercise 5 should be expressible as
Click here to view code image
Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
 */
case class BigNode(binaryTree: BinaryTree*) extends BinaryTree

def sumBinTreeBigNode(binaryTree: BinaryTree): Int = {
  binaryTree match {
    case l: Leaf => l.value
    case n: BigNode => n.binaryTree.map(sumBinTreeBigNode).sum
    case _ => 0
  }
}

val bigTreeNode = BigNode(BigNode(Leaf(3), Leaf(8)), Leaf(2), BigNode(Leaf(5)))
sumBinTreeBigNode(bigTreeNode)




