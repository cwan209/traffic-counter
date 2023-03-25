# Description
This a traffic log counter that takes a traffic log file of timestamp and number of cars and output the statistics of the traffic.

## How to run
clone the repo and run `./gradlew run --args='<INPUT_FILE_PATH>'` with input file path in args on root directory. it can be either a relative path to the root directory
or an absolute path. e.g.
```java
./gradlew run --args='input_file'
```

## How to test
```java
./gradlew test
```

## Assumption
Some traffic logs are missing in the input file and the real traffic is unknown, in order to calculate the contiguous 3 half hours with least cars, the following assumptions are made :
- any traffic log that's missing in the input file and within the range of start and end is considered to be unknown, thus the range is discarded
