#!/bin/bash

# assumes you have java and scala installed and on your path

# once compiled, run `Foo.class` by invoking `scala Foo`

set -e
NAME=$1
echo "building java classes from scala source"
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
mkdir -p ../dist
zip "${NAME}.zip" *.class
cp -f "${NAME}.zip" "../dist/${NAME}.zip"
echo "java classes written to ../dist/${NAME}.zip"
