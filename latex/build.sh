#!/bin/bash

# assumes you have pandoc and pdflatex on your path

pandoc -t beamer -o presentation.pdf --template ./custom.beamer presentation.md
