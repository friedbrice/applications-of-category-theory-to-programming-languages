object OrdinaryPartiality {

  // log(x), null if x ≤ 0
  def log(x: BigDecimal): BigDecimal = {
    val d = x.toDouble
    if (d > 0) BigDecimal(math.log(d))
    else null
  }

  // inv(x) = 1/(1-x), null if x = 1
  def inv(x: BigDecimal): BigDecimal = {
    val d = x.toDouble
    if (d != 1.0) BigDecimal(1.0 / (1.0 - d))
    else null
  }

  // pw(x) = sqrt(x) if 0.5 < x < 1.5,
  //         sin(x)  if 1.5 < x < 3.0,
  //         null otherwise
  def pw(x: BigDecimal): BigDecimal = {
    val d = x.toDouble
    if (d > 0.5 && d < 1.5) BigDecimal(math.sqrt(d))
    else if (d > 1.5 && d < 3.0) BigDecimal(math.sin(d))
    else null
  }

  // f1(x, y) = pw(log(x) + inv(y))
  // incorrectly throws an exception when either log(x) or inv(y) is null
  def f1(x: BigDecimal, y: BigDecimal): BigDecimal = {
    val log_x = log(x)
    val inv_y = inv(y)
    pw(log_x + inv_y)
  }

  // f2(x, y) = pw(log(x) + inv(y))
  // correctly returns null when either log(x) or inv(y) is null
  def f2(x: BigDecimal, y: BigDecimal): BigDecimal = {
    val log_x = log(x)
    if (log_x == null) null else {
      val inv_y = inv(y)
      if (inv_y == null) null else {
        pw(log_x + inv_y)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    // demonstrate that f1 throws an exception
    try {
      println(f1(0, 0))
    } catch { case err: Exception =>
      println(err)
    }
    // demonstrate that f2 returns null
    try {
      println(f2(0, 0))
    } catch { case err: Exception =>
      println(err)
    }
  }
}
