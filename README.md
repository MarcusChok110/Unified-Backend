# Unified (Backend)

This a REST API and WebSocket server built with Java and Spring Boot for the Unified chat application. The application
is connected to a local MySQL database using Spring Data JPA and endpoints are protected by Auth0 and Spring Security.
By default, the application is hosted locally at http://localhost:8080/.

The authentication / authorization of this application was provided with help from Auth0. If you would like to learn more about this process, please visit [this article](https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/).

## Database Schemas (JPA Entities)

### Message

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false, updatable = false)
private Long id;

@Column(columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date timestamp;

private String authorId;

private String author;

private String content;
```

## API Endpoints

### Messages

- `GET /messages/` — returns all messages from the database
- `POST /messages/` — creates a new message from request body
- `GET /messages/:id` — returns a single message from the database
- `PUT /messages/:id` — edits a single message according to the request body
- `DELETE /messages/:id` — deletes a message

## WebSocket Endpoints

- `/unified-websocket/` — for broker url to open connection
- `/app/add` — to add a new message to the chat
- `/app/delete` — to delete a message from the chat
- `/topic/responses` — where responses get sent

## Dependencies

These are the dependencies of the application as defined in the `pom.xml` file:

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-resource-server</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-jose</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>
```

## Local Installation

Currently, the application is not deployed or hosted on the web. To use it, you must install it locally. Firstly, ensure
you have IntelliJ or another IDE installed on your system, as well as Java. Then, follow the following steps:

1. Download / clone the repository.
2. Open the project and install the Maven dependencies.
3. Create a MySQL database and set up an `application.properties` file in the src/main/resources directory with the
   following values:

```
# MySQL Configuration
spring.datasource.url=<MYSQL_URI>
spring.datasource.username=<MYSQL_USERNAME>
spring.datasource.password=<MYSQL_PASSWORD>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Auth0 Configuration
auth0.audience=<AUTH0_AUDIENCE>
spring.security.oauth2.resourceserver.jwt.issuer-uri=<AUTH0_DOMAIN>

```

4. Run the application in the IDE. The server should now be running on http://localhost:8080/.

## Todo

- Split up WebSocket topic response endpoints
- Implement edit message REST and WebSocket endpoints
