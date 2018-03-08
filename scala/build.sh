#!/bin/bash

# assumes you have java and scala installed and on your path
# once compiled, run `Foo.class` by invoking `scala Foo`

set -e
NAME=$1
HERE=$(basename `pwd`)
echo "building classes from scala source"
(
  PROCS_46574465=( )
  (
    scalac OrdinaryPartiality.scala
  ) & PROCS_46574465+=( $! ) && (
    scalac LibMaybe.scala
    scalac MonadicPartiality.scala
  ) & PROCS_46574465+=( $! ) && (
    scalac OrdinaryIO.scala
  ) & PROCS_46574465+=( $! ) && (
    scalac LibIO.scala
    scalac MonadicIO.scala
  )
  for PROC in "${PROCS_46574465[@]}"; do
    wait "$PROC" || exit
  done
)
zip --quiet out.zip *.class
echo "classes written to ${HERE}/out.zip"
