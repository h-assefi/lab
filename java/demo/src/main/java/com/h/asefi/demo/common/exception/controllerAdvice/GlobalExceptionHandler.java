package com.h.asefi.demo.common.exception.controllerAdvice;

import com.h.asefi.demo.common.exception.exceptionFormat.ExceptionMessage;
import com.h.asefi.demo.common.exception.exceptionTypes.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code GlobalExceptionHandler} is a centralized exception handler for the
 * entire Spring Boot application.
 * <p>
 * It extends {@link ResponseEntityExceptionHandler} and uses
 * {@link ControllerAdvice} to intercept exceptions
 * thrown by controllers and services, providing consistent and structured error
 * responses for API clients.
 * </p>
 *
 * <h2>Features</h2>
 * <ul>
 * <li>Handles all custom exceptions (e.g.,
 * {@link com.h.asefi.demo.common.exception.exceptionTypes.BadRequestException},
 * {@link com.h.asefi.demo.common.exception.exceptionTypes.ResourceNotFoundException},
 * etc.) and maps them to appropriate HTTP status codes.</li>
 * <li>Handles validation errors and returns a map of field errors for invalid
 * requests.</li>
 * <li>Formats error responses using the
 * {@link com.h.asefi.demo.common.exception.exceptionFormat.ExceptionMessage}
 * class,
 * including details such as app name, context, message, details, remote user,
 * and server address.</li>
 * <li>Provides a consistent error response structure for all API
 * endpoints.</li>
 * <li>Supports localization of exception messages via
 * {@code ExceptionMessageResolver} (if configured).</li>
 * </ul>
 *
 * <h2>Usage</h2>
 * <ul>
 * <li>Throw custom exceptions in your services or controllers to signal
 * specific error conditions.</li>
 * <li>All exceptions are handled globally, so clients receive consistent and
 * informative error responses.</li>
 * <li>Extend this handler as needed to support additional exception types or
 * custom error formatting.</li>
 * </ul>
 *
 * <h2>Example Error Response</h2>
 * 
 * <pre>
 * {
 *   "appName": "demo",
 *   "context": "/api",
 *   "message": "Resource not found",
 *   "details": ["User with id 123 not found"],
 *   "remoteUser": null,
 *   "serverAddress": "localhost"
 * }
 * </pre>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.servlet.context-path:}")
    private String context;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex,
            WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ExceptionMessage> customException(CustomException ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(RepositoryException.class)
    public final ResponseEntity<ExceptionMessage> repositoryException(RepositoryException ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionMessage> badRequestException(BadRequestException ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> result = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            result.put(field, error.getDefaultMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public final ResponseEntity<ExceptionMessage> unAuthorizedException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> accessDeniedException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex, webRequest), HttpStatus.FORBIDDEN);
    }

    private ExceptionMessage getExceptionMessage(Exception ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ExceptionMessage error = new ExceptionMessage();
        error.setMessage(ex.getMessage());
        error.setDetails(details);
        error.setRemoteUser(webRequest.getRemoteUser());
        error.setAppName(appName);
        error.setContext(context);
        error.setServerAddress(webRequest.getDescription(false));
        return error;
    }

}