#!/bin/bash

# assumes you have pandoc and pdflatex installed and on your path

set -e
NAME=$1
HERE=$(basename `pwd`)
echo "building pdf from latex source"
pandoc -t beamer -o presentation.pdf --template ./custom.beamer presentation.md
echo "pdf written to ${HERE}/presentation.pdf"
