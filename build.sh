#!/bin/bash

mkdir -p build && cd build

rm -rf *
cp ../rebuild.sh ../Makefile .

./rebuild.sh
