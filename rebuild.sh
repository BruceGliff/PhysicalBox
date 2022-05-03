#!/bin/bash

OUT=bin

mkdir -p $OUT

make

cd $OUT
cp ../../launch.html .
appletviewer file://$PWD/launch.html
