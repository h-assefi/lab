# Exception Module

This module provides a structured and extensible approach for handling exceptions in Java Spring applications. It defines a hierarchy of custom exception types, a global exception handler for consistent API error responses, and utilities for exception message resolution and formatting.

---

## Folder Structure

```
exception/
├── controllerAdvice/
│   └── GlobalExceptionHandler.java
├── exceptionFormat/
│   ├── ExceptionMessage.java
│   └── ExceptionMessageResolver.java
├── exceptionTypes/
│   ├── AccessDeniedException.java
│   ├── BadRequestException.java
│   ├── BaseException.java
│   ├── ConflictException.java
│   ├── CustomException.java
│   ├── RepositoryException.java
│   ├── ResourceNotFoundException.java
│   ├── UnAuthorizedException.java
```

---

## Components

### 1. Exception Types (`exceptionTypes/`)

- **BaseException.java**  
  The root custom exception class, extending `RuntimeException`. All other custom exceptions inherit from this class.

- **AccessDeniedException.java**  
  Thrown when a user tries to access a resource without sufficient permissions. Returns HTTP 403.

- **BadRequestException.java**  
  Indicates a malformed or invalid request. Returns HTTP 400.

- **ConflictException.java**  
  Used for resource conflicts, such as duplicate entries. Returns HTTP 409.

- **CustomException.java**  
  A generic exception for custom application errors. Returns HTTP 417.

- **RepositoryException.java**  
  Used for errors related to data repositories or persistence. Returns HTTP 417.

- **ResourceNotFoundException.java**  
  Thrown when a requested resource cannot be found. Returns HTTP 404.

- **UnAuthorizedException.java**  
  Indicates authentication failure or missing credentials. Returns HTTP 401.

All exception types are annotated with `@ResponseStatus` to map them to appropriate HTTP status codes.

---

### 2. Exception Formatting (`exceptionFormat/`)

- **ExceptionMessage.java**  
  A POJO that structures error details for API responses, including fields like `appName`, `context`, `message`, `details`, `remoteUser`, and `serverAddress`.

- **ExceptionMessageResolver.java**  
  Utility for resolving localized exception messages using Spring's `MessageSource`.

---

### 3. Global Exception Handling (`controllerAdvice/`)

- **GlobalExceptionHandler.java**  
  A `@ControllerAdvice` class that intercepts exceptions thrown by controllers and returns structured error responses using `ExceptionMessage`.
  - Handles all custom exceptions and maps them to their respective HTTP status codes.
  - Handles validation errors and formats them as a map of field errors.
  - Provides a consistent error response structure for all API endpoints.

---

## How It Works

- When an exception is thrown in the application, the `GlobalExceptionHandler` catches it and returns a structured JSON response.
- Custom exceptions provide meaningful HTTP status codes and messages.
- Validation errors are automatically formatted and returned as part of the response.
- Exception messages can be localized using the `ExceptionMessageResolver`.

---

## Usage

1. **Throw Custom Exceptions**  
   Use the provided exception types in your services or controllers to signal specific error conditions:

   ```java
   if (userNotFound) {
       throw new ResourceNotFoundException("User not found");
   }
   ```

2. **Consistent API Error Responses**  
   All exceptions are handled globally, so clients receive consistent and informative error responses.

3. **Extend as Needed**  
   Add new exception types by extending `BaseException` and annotating with `@ResponseStatus`.

---

## Summary

The exception module ensures robust, maintainable, and user-friendly error handling for your Spring Boot applications. It provides a clear structure for defining, throwing, and handling exceptions, resulting in better API design and easier
