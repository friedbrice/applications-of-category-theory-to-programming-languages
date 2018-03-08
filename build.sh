#!/bin/bash

# assumes you have pandoc and pdflatex installed and on your path
# assumes you have java and scala installed and on your path

set -e
NAME='applications-of-category-theory-to-programming-languages'
echo "building $NAME"
mkdir -p dist
(
  PROCS_78935790=( )
  (
    cd latex
    ./build.sh "$NAME"
    cp -f presentation.pdf "../dist/${NAME}.pdf"
    echo "pdf copied to dist/${NAME}.pdf"
  ) & PROCS_78935790+=( $! ) && (
    cd scala
    ./build.sh "$NAME"
    cp -f out.zip "../dist/${NAME}.zip"
    echo "classes copied to dist/${NAME}.zip"
  )
  for PROC in "${PROCS_78935790[@]}"; do
    wait "$PROC" || exit
  done
)
case $1 in
  clean)
    echo "cleaning build files"
    rm scala/*.class
    rm scala/*.zip
    rm latex/*.pdf
    ;;
  *)
    ;;
esac
echo "build successful"
