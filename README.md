# Build-Analyzer [![Build Status](https://travis-ci.org/jose/build-analyzer.svg?branch=master)](https://travis-ci.org/jose/build-analyzer)

Ant build file analyzer that extracts compile-targets, developer included/excluded test patterns, and a list of developer ran tests.

## Running the Program

`java -jar build-analyzer-[version].jar [path-to-project-directory] [path-to-place-output-file] [build-file-name]`

### Program Output

- `[path-to-place-output-file]/targets.txt` - includes target that compiles source and target that compiles test source
- `[path-to-place-output-file]/includes.txt` - includes developer-included test patterns
- `[path-to-place-output-file]/excludes.txt` - includes developer-excluded test patterns
- `[path-to-place-output-file]/developer-included-tests.txt` - all the tests that are included to run by developers (modulo excluded test patterns)
