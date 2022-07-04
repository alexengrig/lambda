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

[ConsumerCurrying](src/main/java/dev/alexengrig/util/lambda/ConsumerCurrying.java):

```java
List<String> list = new LinkedList<>();
Stream.of("1", "2", "3")
              // s -> list.add(0, s)
        .forEach(ConsumerCurrying.left2(list::add, 0));
String value = String.join("", list);
assert "321".equals(value);
```

[FunctionCurrying](src/main/java/dev/alexengrig/util/lambda/FunctionCurrying.java):

```java
String value = Stream.of("1", "22", "333")
          // s -> s.substring(0, 1)
        .map(FunctionCurrying.right3(String::substring, 0, 1))
        .collect(Collectors.joining());
assert "123".equals(value);
```

[PredicateCurrying](src/main/java/dev/alexengrig/util/lambda/PredicateCurrying.java):

```java
String value = Stream.of("-Task", "-Test", "-Text")
             // s -> s.startsWith("Te", 1)
        .filter(PredicateCurrying.right3(String::startsWith, "Te", 1))
        .collect(Collectors.joining());
assert "-Test-Text".equals(value);
```

See full code in [demo](demo).

## Install

### Gradle

```groovy

implementation 'dev.alexengrig:lambda:2.0'
```

### Maven

```xml

<dependency>
    <groupId>dev.alexengrig</groupId>
    <artifactId>lambda</artifactId>
    <version>2.0</version>
</dependency>
```

## License

This project is [licensed](LICENSE) under [Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
