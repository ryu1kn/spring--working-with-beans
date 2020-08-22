#!/bin/bash

set -euo pipefail

total_lines() {
    find "$1" -type file -path '*/src/*' -exec wc -l {} + | tail -1 | awk '{print $1}'
}

for example in example--* ; do
    printf "%4s lines: %s\n" "$(total_lines "$example")" "$example"
done | sort -n
