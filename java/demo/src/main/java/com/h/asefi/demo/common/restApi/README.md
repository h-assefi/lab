# RestApi Module

This module provides a flexible and extensible abstraction for making HTTP requests (GET, POST, PUT, DELETE) in Java applications using Spring. It supports multiple HTTP client implementations (such as Java's HttpClient and Spring's RestTemplate) and allows for easy configuration, conversion, and extension.

## Structure

```
restApi/
├── README.md
├── RestApi.java
├── RestApiService.java
├── RestApiServiceImpl.java
├── config/
│   └── RestApiConfiguration.java
├── converter/
│   └── RestApiConverter.java
├── httpClient/
│   └── HttpClientApi.java
├── restTemplate/
│   └── RestTemplateApi.java
└── type/
    └── RestApiToolService.java
```

### Main Components

- **RestApi.java**  
  Defines a generic interface for HTTP operations (GET, POST, PUT, DELETE) with headers and body support.

- **RestApiService.java**  
  Provides a higher-level service interface for HTTP operations, with overloaded methods for convenience (with/without headers and body).

- **RestApiServiceImpl.java**  
  Implements `RestApiService`, delegating calls to a chosen `RestApi` implementation (e.g., HttpClientApi or RestTemplateApi).

- **config/RestApiConfiguration.java**  
  Contains Spring configuration for wiring up the appropriate HTTP client implementation and related beans.

- **converter/RestApiConverter.java**  
  Handles conversion between different data formats or representations used in HTTP requests/responses.

- **httpClient/HttpClientApi.java**  
  Implements the `RestApi` interface using Java's built-in `HttpClient`.

- **restTemplate/RestTemplateApi.java**  
  Implements the `RestApi` interface using Spring's `RestTemplate`.

- **type/RestApiToolService.java**  
  Provides utility or tool services related to the RestApi module.

## How It Works

1. **Abstraction**:  
   The `RestApi` interface abstracts HTTP operations, allowing for different implementations (e.g., HttpClient, RestTemplate).

2. **Service Layer**:  
   `RestApiService` offers a user-friendly API with overloaded methods for various use cases (with/without headers/body).

3. **Implementation Selection**:  
   The actual HTTP client implementation is selected and configured via Spring in `RestApiConfiguration.java`.

4. **Conversion**:  
   `RestApiConverter` ensures that request and response bodies are properly serialized/deserialized.

## Usage

### 1. Add Dependency

Ensure your project includes Spring Boot and any required HTTP client dependencies.

### 2. Configuration

By default, the configuration in `RestApiConfiguration.java` will wire up the appropriate beans. You can customize which HTTP client to use by modifying this configuration.

### 3. Inject and Use the Service

Inject `RestApiService` into your Spring components:

```java
import com.h.asefi.demo.common.restApi.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final RestApiService restApiService;

    @Autowired
    public MyService(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    public void callExternalApi() throws Exception {
        String url = "https://api.example.com/data";
        ResponseEntity<?> response = restApiService.get(url);
        // Handle the response
    }
}
```

### 4. Advanced Usage

You can use overloaded methods to include headers or a request body:

```java
Map<String, String> headers = Map.of("Authorization", "Bearer token");
Object requestBody = ...; // Your request body

// GET with headers
restApiService.get(url, headers);

// POST with headers and body
restApiService.post(url, headers, requestBody);
```

## Extending

To add a new HTTP client implementation:
1. Implement the `RestApi` interface.
2. Register your implementation in the Spring configuration (`RestApiConfiguration.java`).

## Summary

This module provides a clean, extensible, and testable way to perform HTTP operations in your Java/Spring applications, abstracting away the details of the underlying HTTP client and offering a consistent API for your services.