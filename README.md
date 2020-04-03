# Maven Auto Database to Liquibase Generator Plugin

[![TravisCI Status][travis-image]][travis-url]
[![Codecov Status][codecov-image]][codecov-url]
[![Central OSSRH][oss-nexus-image]][oss-nexus-url]
[![Central Maven][central-image]][central-url]


Maven plugin to generate xml liquibase files from database

## Support

- DB2
- Oracle DataBase 11g and 12c (not yet)
- Basic PostgresSQL (not yet)
- Basic SQL Server (Tested 2017, Driver jTDS) (not yet)
- Command line for driver, user, pass and connectionString parameters

## DB2

- TABLES
- AUTO INCREMENT (always increment and start by 1)
- DEFAULT VALUES (only string and numeric)
- INDEX CONSTRAINS
- UNIQUE CONSTRAINS
- PRIMARY KEY CONSTRAINS
- FOREIGN KEY CONSTRAINS (missing onDelete and onUpdate actions yet)

## POM properties

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

## POM dependencies

```xml        
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.5</version>
</dependency>
```

## DB2

```xml        
<dependency>
    <groupId>com.ibm.db2</groupId>
    <artifactId>jcc</artifactId>
    <version>11.5.0.0</version>
</dependency>
```

## Oracle

```xml
<dependency>
    <groupId>com.jslsolucoes</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>11.2.0.1.0</version>
</dependency>
```

## PostgreSQL

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.1.4</version>
</dependency>
```

## SQL Server

```xml
<dependency>
    <groupId>net.sourceforge.jtds</groupId>
    <artifactId>jtds</artifactId>
    <version>1.3.1</version>
</dependency>
```

## POM plugin config

```xml
<plugin>
    <groupId>com.github.yadickson</groupId>
    <artifactId>autodblq</artifactId>
    <version>...</version>
    <executions>
        <execution>
            <goals>
                <goal>generator</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <driver>...</driver>
        <url>...</url>
        <username>...</username>
        <password>...</password>
        <author>...</author>
        <version>...</version>
        <tables>
            <param>...</param>
            <param>...</param>
        </tables>
        <schemas>
            <param>...</param>
            <param>...</param>
        </schemas>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>...driver..</groupId>
            <artifactId>..driver..</artifactId>
            <version>..version..</version>
        </dependency>
    </dependencies>
</plugin>
```

### driver (required)

JDBC Driver class name, examples:

```
com.ibm.db2.jcc.DB2Driver
```

```
oracle.jdbc.driver.OracleDriver
```

```
org.postgresql.Driver
```

```
net.sourceforge.jtds.jdbc.Driver
```

### url (required)

Database connection string, examples:

```
jdbc:db2://${host}:${port}/${db}
```

```
jdbc:oracle:thin:@${host}:${port}:${db}
```

```
jdbc:postgresql://${host}:${port}/${db}
```

```
jdbc:jtds:sqlserver://${host}:${port}/${db}
```

### username (required)

Database username

### password (required)

Database password

### tables (optional)

Tables to include, example XY.*

### schemas (optional)

Schemas to include, example MN.*

# Command line support

```xml
<plugin>
    <groupId>com.github.yadickson</groupId>
    <artifactId>autodblq</artifactId>
    <version>...</version>
    <configuration>
        <tables>
            <param>...</param>
            <param>...</param>
        </tables>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>...driver..</groupId>
            <artifactId>..driver..</artifactId>
            <version>..version..</version>
        </dependency>
    </dependencies>
</plugin>
```

```
$ mvn autodblq:generator -Dautodblq.driver=... -Dautodblq.url=... -Dautodblq.username=... -Dautodblq.password=...
```

## POM Basic Configuration (include tables)

```xml
<plugin>
    <groupId>com.github.yadickson</groupId>
    <artifactId>autodblq</artifactId>
    <version>...</version>
    <executions>
        <execution>
            <goals>
                <goal>generator</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <driver>...</driver>
        <url>...</url>
        <username>...</username>
        <password>...</password>
        <tables>
            <param>...</param>
            <param>...</param>
        </tables>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>...driver..</groupId>
            <artifactId>..driver..</artifactId>
            <version>..version..</version>
        </dependency>
    </dependencies>
</plugin>
```

## POM Basic Configuration (include schemas)

```xml
<plugin>
    <groupId>com.github.yadickson</groupId>
    <artifactId>autodblq</artifactId>
    <version>...</version>
    <executions>
        <execution>
            <goals>
                <goal>generator</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <driver>...</driver>
        <url>...</url>
        <username>...</username>
        <password>...</password>
        <schemas>
            <param>...</param>
            <param>...</param>
        </schemas>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>...driver..</groupId>
            <artifactId>..driver..</artifactId>
            <version>..version..</version>
        </dependency>
    </dependencies>
</plugin>
```

[travis-image]: https://travis-ci.org/yadickson/autodblq.svg?branch=master
[travis-url]: https://travis-ci.org/yadickson/autodblq

[codecov-image]: https://codecov.io/gh/yadickson/autodblq/branch/master/graph/badge.svg?branch=master
[codecov-url]: https://codecov.io/gh/yadickson/autodblq

[oss-nexus-image]: https://img.shields.io/nexus/r/https/oss.sonatype.org/com.github.yadickson/autodblq.svg
[oss-nexus-url]: https://oss.sonatype.org/#nexus-search;quick~autodblq

[central-image]: https://maven-badges.herokuapp.com/maven-central/com.github.yadickson/autodblq/badge.svg
[central-url]: https://maven-badges.herokuapp.com/maven-central/com.github.yadickson/autodblq
