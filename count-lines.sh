#!/bin/bash

set -euo pipefail

total_lines() {
    find "$1" -type file -path '*/src/*' -exec bash -c "grep -v '^import ' {} | wc -l" ';' \
        | awk '{sum += $1}; END {print sum}'
}

for example in example--* ; do
    printf "%4s lines: %s\n" "$(total_lines "$example")" "$example"
done | sort -n
