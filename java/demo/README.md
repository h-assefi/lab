# Demo Java Project

This project is a modular, production-ready Java application built with Spring Boot. It demonstrates best practices for building scalable, maintainable, and testable enterprise applications. The codebase is organized into several feature modules, each responsible for a specific concern such as REST API abstraction, caching, exception handling, status/health checks, and database management.

---

## Key Features & Technologies

- **Spring Boot**: Rapid application development with auto-configuration and embedded server.
- **Spring Caching (Caffeine)**: High-performance, configurable in-memory caching.
- **Liquibase**: Database schema versioning and migration.
- **PostgreSQL**: Reliable, open-source relational database for persistent storage.
- **Custom Exception Handling**: Structured error responses and custom exception types.
- **REST API Abstraction**: Flexible HTTP client abstraction supporting multiple implementations.
- **Health Check & Maintenance Mode**: Standardized endpoints for system status and maintenance toggling.
- **Modular Design**: Separation of concerns via packages and interfaces.
- **Unit Testing**: JUnit 5 for automated tests.
- **Configuration via Properties**: Externalized configuration for environment-specific settings.

---

## Project Structure

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/h/asefi/demo/
│   │   │       ├── common/
│   │   │       │   ├── cache/
│   │   │       │   ├── exception/
│   │   │       │   ├── restApi/
│   │   │       ├── setting/
│   │   │       ├── status/
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── db/changelog/
│   ├── test/
│   │   └── java/
│   │       └── com/h/asefi/demo/
├── pom.xml
└── README.md
```

---

## Module Overview

### 1. Common Modules

#### - **Cache Module** ([common/cache](src/main/java/com/h/asefi/demo/common/cache/README.md))

- Uses Caffeine for fast, configurable caching.
- Multiple cache regions (hour/day/week) with different expiration policies.
- Utility service for cache eviction and updates.

#### - **Exception Module** ([common/exception](src/main/java/com/h/asefi/demo/common/exception/README.md))

- Custom exception types for API error handling.
- Global exception handler for consistent error responses.
- Exception message formatting and localization.

#### - **REST API Module** ([common/restApi](src/main/java/com/h/asefi/demo/common/restApi/README.md))

- Abstraction for HTTP operations (GET, POST, PUT, DELETE).
- Supports multiple HTTP client implementations (Java HttpClient, Spring RestTemplate).
- Converter for request/response serialization.
- Service layer for easy API usage.

### 2. Status Module ([status](src/main/java/com/h/asefi/demo/status/README.md))

- `/status` endpoint for health checks (used by load balancers/monitors).
- `/status/maintainable/{status}` endpoint to toggle maintenance mode.
- Caching for efficient status checks.
- DTOs for structured status responses.

### 3. Database Management

#### - **Liquibase Changelog** ([db/changelog](src/main/resources/db/changelog/README.md))

- YAML-based changelogs for schema migrations.
- Versioned folders for incremental upgrades.
- Precondition logic for safe data changes.

#### - **Database Creator**

- Initializes database and schema at startup if not present.
- Configured via application properties.

#### - **PostgreSQL Integration**

- The project uses PostgreSQL as the main relational database.
- Database connection settings (URL, username, password) are managed in `application.properties`.
- Liquibase changelogs ensure the schema is always up-to-date and versioned.
- PostgreSQL is used for persistent storage of application data, supporting transactions and advanced SQL features.

---

## How It Works

- **Startup**:  
  The application initializes the database and schema, loads configuration, and wires up all modules via Spring Boot.

- **Caching**:  
  Caffeine caches are configured and managed via Spring. Use the `CacheService` to evict or update cache entries.

- **REST API**:  
  Use `RestApiService` for HTTP calls. Choose between HttpClient or RestTemplate via configuration.

- **Exception Handling**:  
  All exceptions are caught by a global handler, returning structured JSON error responses.

- **Health Checks**:  
  `/status` endpoint provides system availability. Maintenance mode can be toggled for deployments.

- **Database Migration**:  
  Liquibase applies schema changes automatically at startup.

- **Database Storage**:  
  PostgreSQL stores all persistent data. The schema is managed and migrated using Liquibase.

---

## Usage

### 1. Build & Run

```sh
mvn clean install
mvn spring-boot:run
```

### 2. Configuration

Edit `src/main/resources/application.properties` for environment-specific settings (DB, cache, etc.).  
Example PostgreSQL settings:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/demo_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
```

### 3. Health Check

- `GET /api/status` — Returns system status (`OK` or `MAINTENANCE`).

### 4. Maintenance Mode

- `POST /api/status/maintainable/{status}` — Enable/disable maintenance mode.

### 5. Cache Management

Use `CacheService` in your beans to manage cache entries.

### 6. Database Migration

Liquibase changelogs are applied automatically. Add new changesets in `db/changelog/vX/`.

---

## Extending

- **Add new REST API client**: Implement `RestApi` and register in configuration.
- **Add new cache region**: Define new bean in `CacheConfig.java`.
- **Add new exception type**: Extend `BaseException` and annotate with `@ResponseStatus`.
- **Add new status**: Update `Status.java` enum and related DTOs.
- **Add new database entities**: Create new JPA entities and update Liquibase changelogs.

---

## Testing

Unit tests are located in `src/test/java/com/h/asefi/demo/`. Run with:

```sh
mvn test
```

---

## Summary

This project demonstrates a clean, modular approach to building enterprise Java applications with Spring Boot. It leverages modern tools and best practices for caching, exception handling, REST abstraction, health checks, PostgreSQL integration, and database migration, making it easy to extend and maintain complex applications.
