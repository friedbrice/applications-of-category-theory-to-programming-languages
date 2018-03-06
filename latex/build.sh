#!/bin/bash

# assumes you have pandoc and pdflatex installed and on your path

set -e
NAME=$1
echo "building pdf from latex source"
pandoc -t beamer -o presentation.pdf --template ./custom.beamer presentation.md
mkdir -p ../dist
cp -f presentation.pdf "../dist/${NAME}.pdf"
echo "pdf written to ../dist/${NAME}.pdf"
