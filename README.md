# Spring DI with different styles

This repository contains different styles of bean definition in Spring Framework.

* XML based configuration: [example--xml](./example--xml)
* Annotation based configuration: [example--annotation](./example--annotation)
* Java config based configuration: [example--java-config](./example--java-config)
* (No Spring beans: [example--no-beans](./example--no-beans))

The last, No Spring beans, is for demonstrating how it would look if we don't
use Spring DI mechanism to wire-up our application components.

Each example:

* has the same web app. Bookstore.
* contains unit tests that uses the corresponding bean definition style.
* is written in Kotlin (as the original audiences are Kotlin devs...)

## Prerequisites

* JDK v11~

## Usage

To run the service in each example directory, run:

```sh
./gradlew build && java -jar bookstore-1.0-SNAPSHOT.jar
```

You can test it with:

```sh
curl -i localhost:8080/books
```

To run the unit test:

```sh
./gradlew test
```

## Misc: Number of lines of each example

Lines in files under `src` directory of each example, excluding `import` statements.
You can tweak the script to include `import` too.

```sh
$ ./count-lines.sh
  91 lines: example--no-beans
 100 lines: example--hand-wired
 120 lines: example--java-config
 126 lines: example--annotation
 131 lines: example--xml
```

## References

* [The IoC Container - Core Technologies](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans)
  * [Dependency Resolution Process](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-dependency-resolution)
* [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot/)
* [Building web applications with Spring Boot and Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

### Spring functional web framework

* [New in Spring 5: Functional Web Framework](https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework)
  * [Spring 5 Functional Web Framework Sample](https://github.com/poutsma/web-function-sample)
* [78. Embedded Web Servers - Spring docs](https://docs.spring.io/spring-boot/docs/2.1.10.RELEASE/reference/html/howto-embedded-web-servers.html)
* [Configure a Spring Boot Web Application](https://www.baeldung.com/spring-boot-application-configuration)
* [Getting Started - 23. WebFlux framework](https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html#web-reactive-getting-started)
* [Web on Reactive Stack](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-new-framework)

### Testing

* [Testing - Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#integration-testing-annotations-spring)
* [Spring 3.1 M2: Testing with @Configuration Classes and Profiles](https://spring.io/blog/2011/06/21/spring-3-1-m2-testing-with-configuration-classes-and-profiles)
* [26. Testing - Spring Boot Features](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing)
* [41. Testing - Part IV. Spring Boot features](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [SpringRunner vs SpringBootTest - StackOverflow](https://stackoverflow.com/questions/58901288/springrunner-vs-springboottest#answer-58902051)
