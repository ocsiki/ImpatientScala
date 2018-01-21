for (c <- 0 to 1;i <- "Hello") yield i


var r = 1
for (i <- 1 to 3) r = r*i
print(r)

def fact(n: Int): Int = if (n <= 1) n else n * fact(n-1)

println(fact(5))

val a  = 1 to 5