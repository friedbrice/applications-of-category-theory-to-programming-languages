import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source

// Example due to Tony Morris and Mark Hibberd: https://github.com/data61/fp-course
object OrdinaryIO {

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

  def program1: Unit = {
    val file = "/tmp/file"

    writeFile(file, "abcdef")
    val x = readFile(file)
    println(x)
    writeFile(file, "ghijkl")
    val y = readFile(file)
    println(x + y)
  }

  def program2: Unit = {
    val file = "/tmp/file"
    val expr = readFile(file)

    writeFile(file, "abcdef")
    val x = expr
    println(x)
    writeFile(file, "ghijkl")
    val y = expr
    println(x + y)
  }

  def combined: Unit = {
    println("OrdinaryIO")
    println("==========")
    println("")
    println("program1")
    println("--------")
    program1
    println("")
    println("program2")
    println("--------")
    program2
    println("")
  }

  def main(args: Array[String]): Unit = combined
}
