#!/bin/sh

cd "`dirname "$0"`"

javac *.java

cd "`dirname "$0"`"

java starter

echo 'Press enter to close'
read