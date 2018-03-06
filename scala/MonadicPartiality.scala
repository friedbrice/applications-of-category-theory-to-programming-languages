import LibMaybe.{Maybe, guard, or}

object MonadicPartiality {

  // log(x), null if x ≤ 0
  def log(x: BigDecimal): Maybe[BigDecimal] = {
    val d = x.toDouble
    guard(d > 0, BigDecimal(math.log(d)))
  }

  // inv(x) = 1/(1-x), null if x = 1
  def inv(x: BigDecimal): Maybe[BigDecimal] = {
    val d = x.toDouble
    guard(d != 1, BigDecimal(1 / (1 - d)))
  }

  // pw(x) = sqrt(x) if 0.5 < x < 1.5,
  //         sin(x)  if 1.5 < x < 3.0,
  //         cos(x)  if 3.0 < x < 4.5,
  //         null otherwise
  def pw(x: BigDecimal): Maybe[BigDecimal] = {
    val d = x.toDouble
    or( guard(d > 0.5 && d < 1.5, BigDecimal(math.sqrt(d))) ,
        guard(d > 1.5 && d < 3.0, BigDecimal( math.sin(d))) )
  }

  // f(x, y) = pw(log(x) + inv(y))
  def f(x: BigDecimal, y: BigDecimal): Maybe[BigDecimal] =
    log(x)                              bind { log_x =>
    inv(y)                              bind { inv_y =>
    pw(log_x + inv_y)                   }}

  def main(args: Array[String]): Unit = {
    Maybe.extract(f(0, 0))(println("nothing"), x => println(x))
  }
}
