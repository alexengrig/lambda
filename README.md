# lambda

[![Maven Central](https://img.shields.io/maven-central/v/dev.alexengrig/lambda.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22dev.alexengrig%22%20AND%20a:%22lambda%22)
[![Javadocs](https://www.javadoc.io/badge/dev.alexengrig/lambda.svg)](https://www.javadoc.io/doc/dev.alexengrig/lambda)
[![GitHub](https://img.shields.io/github/license/alexengrig/lambda?style=flat&&color=informational)](LICENSE)

Add-ons for [java.util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html).

[TerConsumer](src/main/java/dev/alexengrig/util/lambda/TerConsumer.java):

```java
TerConsumer<byte[], Integer, Integer> fn = (byte[] bytes, Integer offset, Integer length) -> new String(bytes, offset, length);
TerConsumer<byte[], Integer, Integer> ref = String::new;
```

[TerFunction](src/main/java/dev/alexengrig/util/lambda/TerFunction.java):

```java
TerFunction<String, Integer, Integer, String> fn = (String s, Integer b, Integer e) -> s.substring(b, e);
TerFunction<String, Integer, Integer, String> ref = String::substring;
```

[TerPredicate](src/main/java/dev/alexengrig/util/lambda/TerPredicate.java):

```java
TerPredicate<Long, Long, Long> predicate3 = (f, s, t) -> f > 0 ^ s > 0 ^ t > 0;
```

[Currying](src/main/java/dev/alexengrig/util/lambda/Currying.java):

```java
String value = Stream.of("1", "22", "333")
          // s -> s.substring(0, 1)
        .map(Currying.right3(String::substring, 0, 1))
        .collect(Collectors.joining());
assert "123".equals(value);
```

See full code in [demo](demo).

## Install

### Gradle

```groovy

implementation 'dev.alexengrig:lambda:1.0-SNAPSHOT'
```

### Maven

```xml

<dependency>
    <groupId>dev.alexengrig</groupId>
    <artifactId>lambda</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## License

This project is [licensed](LICENSE) under [Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
