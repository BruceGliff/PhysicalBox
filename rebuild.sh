#!/bin/bash

OUT=bin

mkdir -p $OUT

while IFS= read -r -d '' -u 9
do
    if [[ "$REPLY" =~ .*Main\.java ]]; then
        MAIN="$REPLY"
    else
        echo "compile $REPLY"
        javac -d $OUT "$REPLY"
    fi
done 9< <( find ../src -name "*.java" -type f -exec printf '%s\0' {} + )

echo "compile $MAIN"
javac -cp $OUT -d $OUT "$MAIN"

cd $OUT
cp ../../launch.html .
appletviewer file://$PWD/launch.html
