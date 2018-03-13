import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source
import scala.io.StdIn.readLine

// Portions drawn from Scalaz: https://github.com/scalaz/scalaz
object LibIO {

  abstract class RealWorld {
    def _args: List[String]
  }

  class IO[A](val run: RealWorld => (RealWorld, A)) {

    def bind[B](f: A => IO[B]): IO[B] = new IO(rw => {
      val (rw2, a) = this.run(rw)
      f(a).run(rw2)
    })
  }

  object IO {

    def unit[A](a: A): IO[A] = new IO(rw => (rw, a))

    def lift[A](a: => A): IO[A] = new IO(rw => (rw, a))

    def run(args: List[String], program: IO[Unit]): Nothing = {
      val rw = new RealWorld {
        def _args: List[String] = args
      }
      val (rw2, u) = program.run(rw)
      sys.exit(0)
    }
  }

  def ioArgs: IO[List[String]] = new IO(rw => (rw, rw._args))

  def ioPrintln(str: String): IO[Unit] = IO.lift(println(str))

  def ioReadLine: IO[String] = IO.lift(readLine())

  def ioReadFile(path: String): IO[String] = IO.lift(Unsafe.readFile(path))

  def ioWriteFile(path: String, contents: String): IO[Unit] =
    IO.lift(Unsafe.writeFile(path, contents))

  object Unsafe {

    def readFile(path: String): String = {
      val source = Source.fromFile(path)
      val contents = source.mkString
      source.close()
      contents
    }

    def writeFile(path: String, contents: String): Unit = {
      val writer = new FileWriter(new File(path))
      val buffer = new BufferedWriter(writer)
      buffer.write(contents)
      buffer.close()
      writer.close()
    }
  }
}
