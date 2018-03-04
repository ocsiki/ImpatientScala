/*
4. Add a case class Multiple that is a subclass of the Item class.
For example, Multiple(10, Article("Blackwell Toaster", 29.95)) describes ten toasters.
Of course, you should be able to handle any items, such as bundles or multiples, in the second argument.
Extend the price function to handle this new case.
 */

abstract class Item

case class Article(description: String, price: Double) extends Item

case class Bundle(description: String, discount: Double, items: Item*) extends Item

def price(it: Item): Double = it match {
  case Article(_, p) => p
  case Bundle(_, disc, its @ _*) => its.map(price).sum - disc
  case Multiple(n, items @ _*) => items.map(price).sum * n
}

case class Multiple(number: Int, item: Item*) extends Item

val mul1 = Multiple(10, Article("Blackwell Toaster", 29.95))

price(mul1)