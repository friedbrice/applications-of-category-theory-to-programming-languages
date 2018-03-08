#!/bin/bash

set -e
NAME=$1
HERE=$(basename `pwd`)
which scalac || { echo "could not find scalac, exiting"; exit 1; }
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
  ) & PROCS_46574465+=( $! )
  for PROC in "${PROCS_46574465[@]}"; do
    wait "$PROC" || exit
  done
)
zip --quiet out.zip *.class
echo "classes written to ${HERE}/out.zip"
