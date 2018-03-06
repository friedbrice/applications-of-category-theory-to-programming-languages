import LibIO.{IO, ioPrintln, ioReadFile, ioWriteFile}

// Example due to Tony Morris and Mark Hibberd: https://github.com/data61/fp-course
object MonadicIO {

  def program1: IO[Unit] = {
    val file = "/tmp/file"

    ioWriteFile(file, "abcdef")         bind { _ =>
    ioReadFile(file)                    bind { x =>
    ioPrintln(x)                        bind { _ =>
    ioWriteFile(file, "ghijkl")         bind { _ =>
    ioReadFile(file)                    bind { y =>
    ioPrintln(x + y)                    }}}}}
  }

  def program2: IO[Unit] = {
    val file = "/tmp/file"
    val expr = ioReadFile(file)

    ioWriteFile(file, "abcdef")         bind { _ =>
    expr                                bind { x =>
    ioPrintln(x)                        bind { _ =>
    ioWriteFile(file, "ghijkl")         bind { _ =>
    expr                                bind { y =>
    ioPrintln(x + y)                    }}}}}
  }

  def combined: IO[Unit] =
    ioPrintln("MonadicIO")              bind { _ =>
    ioPrintln("==========")             bind { _ =>
    ioPrintln("")                       bind { _ =>
    ioPrintln("program1")               bind { _ =>
    ioPrintln("--------")               bind { _ =>
    program1                            bind { _ =>
    ioPrintln("")                       bind { _ =>
    ioPrintln("program2")               bind { _ =>
    ioPrintln("--------")               bind { _ =>
    program2                            bind { _ =>
    ioPrintln("")                       }}}}}}}}}}

  def main(args: Array[String]): Unit = IO.run(args.toList, combined)
}
