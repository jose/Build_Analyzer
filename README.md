# Build-Analyzer
- Ant build file analyzer that extracts compile-targets, developer included+excluded test patterns, and a list of developer ran tests.

## Running the Program
- With the jar file (`bin/build-analyzer.jar`): `java -jar build-analyzer.jar [path-to-project-directory] [path-to-place-output-file] [build-file-name]`
- Example: `java -jar build-analyzer.jar users/commons-collections users/commons-collections/analyzer_output build.xml`

### Program Output
- targets.txt - includes target that compiles source and target that compiles test source
- includes.txt - includes developer-included test patterns
- excludes.txt - includes developer-excluded test patterns
- developer-included-tests.txt - all the tests that are included to run by developers (modulo excluded test patterns)
