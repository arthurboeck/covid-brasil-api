# covid-brasil-api
Responsável por retornar dados relacionados ao avanço do covid no brasil.

### Dependencias do Projeto :wrench:

- [Maven](https://maven.apache.org/guides/index.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Lombok](https://projectlombok.org/setup/maven)
- [WireMock](https://wiremock.org/docs/spring-boot/)
- [Mockito](https://site.mockito.org/)

### Connection to Heroku

Heroku URL: `https://springboot-messages.herokuapp.com/`

### Do it Yourself :bomb:

#### How to build:

````
mvn clean install -DskipTests
````

#### How to execute unit tests:

````
mvn test
````

#### How to execute functional tests / integration tests:

````
mvn clean test -Pfunctional-test
````

#### How to run spring boot application:

````
mvn spring-boot:run
````

While application running, the below URLs'll be able: :rocket:

- Actuator: `http://localhost:8080/actuator`
- Swagger: `http://localhost:8080/swagger-ui/index.html`
- Swagger Resources `http://localhost:8080/swagger-resources`
- Aplication URL: `http://localhost:8080/api/v1/`