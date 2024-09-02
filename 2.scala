class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator cannot be zero")

  private val g = gcd(n.abs, d.abs)
  
  val numerator: Int = n / g
  val denominator: Int = d / g

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  
  def neg: Rational = new Rational(-numerator, denominator)

  
  def add(that: Rational): Rational = {
    new Rational(
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )
  }

  def sub(that: Rational): Rational = {
    new Rational(
      numerator * that.denominator - that.numerator * denominator,
      denominator * that.denominator
    )
  }

  override def toString: String = s"$numerator/$denominator"
}

object Main {
  def main(args: Array[String]): Unit = {
    val x = new Rational(3, 4)
    val y = new Rational(5, 8)
    val z = new Rational(2, 7)

    val result = y.sub(z)

    val finalResult = x.add(result)

    println(s"Result of x + (y - z) where x=$x, y=$y, z=$z: $finalResult")
  }
}
