#!/bin/bash

# assumes you have java and scala installed and on your path
# once compiled, run `Foo.class` by invoking `scala Foo`

set -e
NAME=$1
HERE=$(basename `pwd`)
echo "building classes from scala source"
(
  (
    scalac OrdinaryPartiality.scala
  ) & (
    scalac LibMaybe.scala && \
    scalac MonadicPartiality.scala
  ) & (
    scalac OrdinaryIO.scala
  ) & (
    scalac LibIO.scala && \
    scalac MonadicIO.scala
  )
)
zip out.zip *.class
echo "classes written to ${HERE}/out.zip"
