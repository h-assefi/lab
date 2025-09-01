# MinIO Spring Boot Demo

This project demonstrates how to integrate [MinIO](https://min.io/)—a high-performance, S3-compatible object storage—with a Java Spring Boot application. It provides a practical example of how to use MinIO as a storage backend for file uploads, downloads, and management in a modern cloud-native Java application.

---

## Purpose

The main goal of this project is to showcase:

- How to connect a Spring Boot application to a MinIO server.
- How to perform common object storage operations (upload, download, list, delete) using the MinIO Java SDK.
- How to structure a Spring Boot application for clean, maintainable, and testable integration with external storage services.
- How to use Docker Compose for local development and testing with a real MinIO instance.

---

## Techniques and Technologies Used

- **Spring Boot**: Provides the foundation for building RESTful APIs and dependency injection.
- **MinIO Java SDK**: Used for interacting with the MinIO server (S3-compatible API).
- **RESTful API Design**: Exposes endpoints for file operations (upload, download, list, delete).
- **Docker Compose**: Used to orchestrate a local MinIO server for development and testing.
- **Configuration via `application.properties`**: All MinIO connection details (endpoint, access key, secret key, bucket name) are externalized for flexibility.
- **Exception Handling**: Custom exception handling for robust API responses.
- **Service Layer Abstraction**: Business logic for MinIO operations is encapsulated in a dedicated service class.
- **DTOs (Data Transfer Objects)**: Used for clean API request/response models.
- **Unit and Integration Testing**: (If present) Demonstrates how to test storage integration in Spring Boot.

---

## Typical Project Structure

```
minio-springboot-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ... (controllers, services, config, exception, dto, etc.)
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── ... (unit/integration tests)
├── docker-compose.yaml      # For running MinIO locally
├── README.md
├── pom.xml                  # Maven dependencies
```

---

## Example Features

- **File Upload**: Upload files to a specified MinIO bucket via REST endpoint.
- **File Download**: Download files from MinIO using a REST endpoint.
- **List Objects**: List all files/objects in a bucket.
- **Delete Object**: Remove files from the bucket.
- **Health Check**: Endpoint to verify MinIO connectivity.

---

## Getting Started

1. **Start MinIO with Docker Compose**
   ```sh
   docker-compose up -d
   ```
2. **Configure `application.properties`**
   - Set MinIO endpoint, access key, secret key, and bucket name.
3. **Run the Spring Boot Application**
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Test the REST Endpoints**
   - Use Postman, curl, or your frontend to interact with the API.

---

## When to Use

- As a template for integrating S3-compatible storage in Java microservices.
- For learning how to use MinIO with Spring Boot.
- As a starting point for building file management or media storage features in your applications.

---

## References

- [MinIO Documentation](https://docs.min.io/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [MinIO Java SDK](https://min.io/docs/minio/linux/developers/java/minio-java.html)

---

```# MinIO Spring Boot Demo

This project demonstrates how to integrate [MinIO](https://min.io/)—a high-performance, S3-compatible object storage—with a Java Spring Boot application. It provides a practical example of how to use MinIO as a storage backend for file uploads, downloads, and management in a modern cloud-native Java application.

---

## Purpose

The main goal of this project is to showcase:
- How to connect a Spring Boot application to a MinIO server.
- How to perform common object storage operations (upload, download, list, delete) using the MinIO Java SDK.
- How to structure a Spring Boot application for clean, maintainable, and testable integration with external storage services.
- How to use Docker Compose for local development and testing with a real MinIO instance.

---

## Techniques and Technologies Used

- **Spring Boot**: Provides the foundation for building RESTful APIs and dependency injection.
- **MinIO Java SDK**: Used for interacting with the MinIO server (S3-compatible API).
- **RESTful API Design**: Exposes endpoints for file operations (upload, download, list, delete).
- **Docker Compose**: Used to orchestrate a local MinIO server for development and testing.
- **Configuration via `application.properties`**: All MinIO connection details (endpoint, access key, secret key, bucket name) are externalized for flexibility.
- **Exception Handling**: Custom exception handling for robust API responses.
- **Service Layer Abstraction**: Business logic for MinIO operations is encapsulated in a dedicated service class.
- **DTOs (Data Transfer Objects)**: Used for clean API request/response models.
- **Unit and Integration Testing**: (If present) Demonstrates how to test storage integration in Spring Boot.

---

## Typical Project Structure

```

minio-springboot-demo/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── ... (controllers, services, config, exception, dto, etc.)
│ │ └── resources/
│ │ └── application.properties
│ └── test/
│ └── java/
│ └── ... (unit/integration tests)
├── docker-compose.yaml # For running MinIO locally
├── README.md
├── pom.xml # Maven dependencies

````

---

## Example Features

- **File Upload**: Upload files to a specified MinIO bucket via REST endpoint.
- **File Download**: Download files from MinIO using a REST endpoint.
- **List Objects**: List all files/objects in a bucket.
- **Delete Object**: Remove files from the bucket.
- **Health Check**: Endpoint to verify MinIO connectivity.

---

## Getting Started

1. **Start MinIO with Docker Compose**
   ```sh
   docker-compose up -d
````

2. **Configure `application.properties`**
   - Set MinIO endpoint, access key, secret key, and bucket name.
3. **Run the Spring Boot Application**
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Test the REST Endpoints**
   - Use Postman, curl, or your frontend to interact with the API.

---

## When to Use

- As a template for integrating S3-compatible storage in Java microservices.
- For learning how to use MinIO with Spring Boot.
- As a starting point for building file management or media storage features in your applications.

---

## References

- [MinIO Documentation](https://docs.min.io/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
