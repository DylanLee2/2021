#!/bin/sh

cd "`dirname "$0"`"

javac *.java
java stacc

echo 'Prees entur 2 clos'
read