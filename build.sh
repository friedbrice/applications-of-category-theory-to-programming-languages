#!/bin/bash

# assumes you have pandoc and pdflatex installed and on your path

set -e
NAME='applications-of-category-theory-to-programming-languages'
echo "building $NAME"
mkdir -p dist
(
  (
    cd latex/ && \
    ./build.sh "$NAME"
  ) & (
    cd scala/ && \
    ./build.sh "$NAME"
  )
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
