#!/bin/bash

pandoc -t beamer -o presentation.pdf --template ./custom.beamer presentation.md
