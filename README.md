# Spring Boot Exercise

Spring Boot 4 · Java 17 · Maven

## Features
- REST APIs with GET, POST methods
- User search with pagination
- AspectJ request/response logging
- H2 in-memory database
- JPQL JOIN query across `users` and `orders`
- External API call (Google) via RestClient
- Unit tests (Mockito + MockMvc)

## Run

```bash
./mvnw spring-boot:run
```

App will be available at http://localhost:8080

### H2 Database Console

Available at http://localhost:8080/h2-console

## Run Tests
```bash
./mvnw test
```

### Postman Use
Can utilise `postman_collection.json` to have the urls and parameters already set.
