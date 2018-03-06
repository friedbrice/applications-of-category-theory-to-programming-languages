#!/bin/bash

scalac OrdinaryPartiality.scala  && \
scalac LibMaybe.scala            && \
scalac MonadicPartiality.scala   && \
scalac OrdinaryIO.scala          && \
scalac LibIO.scala               && \
scalac MonadicIO.scala
