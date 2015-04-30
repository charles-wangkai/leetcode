#!/bin/bash

tr ' ' '\n' < words.txt | sed "/^$/ d" | sort | uniq -c | sed "s/^ *\([0-9].*\) \(.*\)/\2 \1/" | sort -nrk 2