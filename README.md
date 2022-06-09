# TODO

Replace with values:

- `PROJECT_NAME`
- `PROJECT_VERSION`
- `PROJECT_DESCRIPTION`

Add `~/.m2/settings.xml` and replace `OSS_USERNAME`/`OSS_PASSWORD` with values:

```xml

<settings
        xmlns="http://maven.apache.org/SETTINGS/1.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <offline/>
    <pluginGroups/>
    <servers>
        <server>
            <id>ossrh</id>
            <username>OSS_USERNAME</username>
            <password>OSS_PASSWORD</password>
        </server>
    </servers>
    <mirrors/>
    <proxies/>
    <profiles/>
    <activeProfiles/>
</settings>
```

Remove [TODO](#TODO) block.

# PROJECT_NAME

[![Maven Central](https://img.shields.io/maven-central/v/dev.alexengrig/PROJECT_NAME.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22dev.alexengrig%22%20AND%20a:%22PROJECT_NAME%22)
[![Javadocs](https://www.javadoc.io/badge/dev.alexengrig/PROJECT_NAME.svg)](https://www.javadoc.io/doc/dev.alexengrig/PROJECT_NAME)
[![GitHub](https://img.shields.io/github/license/alexengrig/PROJECT_NAME?style=flat&&color=informational)](LICENSE)

PROJECT_DESCRIPTION.

## Install

### Gradle

```groovy

implementation 'dev.alexengrig:PROJECT_NAME:PROJECT_VERSION'
```

### Maven

```xml

<dependency>
    <groupId>dev.alexengrig</groupId>
    <artifactId>PROJECT_NAME</artifactId>
    <version>PROJECT_VERSION</version>
</dependency>
```

## License

This project is [licensed](LICENSE) under [Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
