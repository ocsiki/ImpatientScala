abstract class Item

case class Article(description: String, price: Double) extends Item

case class Bundle(description: String, discount: Double, items: Item*) extends Item

def price(it: Item): Double = it match {

  case Article(_, p) => p

  case Bundle(_, disc, its @ _*) => its.map(price).sum - disc

}