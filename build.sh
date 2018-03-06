#!/bin/bash

# assumes you have pandoc and pdflatex installed and on your path

set -e
NAME='applications-of-category-theory-to-programming-languages'
echo "building $NAME"
mkdir -p dist
(
  (
    cd latex/ && \
    ./build.sh "$NAME" && \
    cp -f presentation.pdf "../dist/${NAME}.pdf" && \
    echo "pdf copied to dist/${NAME}.pdf"
  ) & (
    cd scala/ && \
    ./build.sh "$NAME" && \
    cp -f out.zip "../dist/${NAME}.zip" && \
    echo "classes copied to dist/${NAME}.zip"
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
