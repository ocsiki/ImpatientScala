/*
8. Extend the tree in the preceding exercise so that each nonleaf node stores an operator in addition
to the child nodes.
Then write a function eval that computes the value. Pay attention to the unary minus.
 */
sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(operator: Char, binaryTree: BinaryTree*) extends BinaryTree

def eval(binaryTree: BinaryTree): Int = {
  binaryTree match {
    case l: Leaf => l.value
    case Node('*', rest @_*) => rest.map(eval).product
    case Node('+', rest @_*) => rest.map(eval).sum
    case Node('-', rest @_*) => rest.map(eval).foldLeft(0)(_ - _)
    case _ => 0
  }
}
eval(Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5))))

/*
9. Write a function that computes the sum of the non-None values in a List[Option[Int]]. Don’t use a match statement.
 */
def nonNoneSum(list: List[Option[Int]]) = {
  list.map(it => it.getOrElse(0)).sum
}

nonNoneSum(List(Some(2), None, None, Some(3)))

/*
10. Write a function that composes two functions of type Double => Option[Double], yielding another function
of the same type. The composition should yield None if either function does.

def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
def g(x: Double) = if (x >= 0) Some(sqrt(x)) else None
val h = compose(g, f) // h(x) should be g(f(x))
 */
def compose(f: Double => Option[Double], g: Double => Option[Double]): Double => Option[Double] = {
  (x: Double) => g(x) match {
    case y: Some[Double] => f(y.get)
    case None => None
  }
}
def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
def g(x: Double) = if (x >= 0) Some(scala.math.sqrt(x)) else None

val c = compose(f, g)

c(2)
