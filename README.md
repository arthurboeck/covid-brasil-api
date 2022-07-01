# covid-brasil-api
Responsável por retornar dados relacionados ao avanço do covid no brasil.

### Dependencias do Projeto :wrench:

- [Maven](https://maven.apache.org/guides/index.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [Lombok](https://projectlombok.org/setup/maven)
- [WireMock](https://wiremock.org/docs/spring-boot/)
- [Mockito](https://site.mockito.org/)

### URls

Heroku URL: https://covid-brazil-data-api.herokuapp.com/

Sonar Cloud: https://sonarcloud.io/summary/new_code?id=arthurboeck_covid-brazil-data-api

### Do it Yourself :bomb:

#### Como build:

````
mvn clean install -DskipTests
````

#### Como executar os unit tests:

````
mvn test
````

#### Como executar os functional tests / integration tests:

````
mvn clean test -Pfunctional-test
````

#### Como executar a aplicação spring boot:

````
mvn spring-boot:run
````

Enquanto a aplicação estiver executando, as URLs abaixo serão disponibilizadas: :rocket:

- Actuator: `http://localhost:8080/actuator`
- Swagger: `http://localhost:8080/swagger-ui.html`
- Swagger Resources `http://localhost:8080/swagger-resources`
- Aplication URL: `http://localhost:8080/api/v1/`