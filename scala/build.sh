#!/bin/bash

# assumes you have java and scala installed and on your path

# once compiled, run `Foo.class` by invoking `scala Foo`

scalac OrdinaryPartiality.scala  && \
scalac LibMaybe.scala            && \
scalac MonadicPartiality.scala   && \
scalac OrdinaryIO.scala          && \
scalac LibIO.scala               && \
scalac MonadicIO.scala
