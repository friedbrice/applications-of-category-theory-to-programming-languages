object LibMaybe {

  abstract class Maybe[A] {
    def bind[B](f: A => Maybe[B]): Maybe[B]
  }

  class Nothing[A] extends Maybe[A] {
    def bind[B](f: A => Maybe[B]) = Maybe.nothing
  }

  class Just[A](val a: A) extends Maybe[A] {
    def bind[B](f: A => Maybe[B]) = f(a)
  }

  object Maybe {

    def unit[A](a: A): Maybe[A] = new Just(a)

    def nothing[A]: Maybe[A] = new Nothing

    def extract[A, B](ma: Maybe[A])
                     (ifNothing: => B, ifJust: A => B): B = ma match {
      case _: Nothing[A] => ifNothing
      case just: Just[A] => ifJust(just.a)
    }
  }

  def guard[A](p: Boolean, a: => A): Maybe[A] =
    if (p) Maybe.unit(a) else Maybe.nothing

  def or[A](left: Maybe[A], right: Maybe[A]): Maybe[A] = left match {
    case _: Just[A] => left
    case _: Nothing[A] => right
  }
}
