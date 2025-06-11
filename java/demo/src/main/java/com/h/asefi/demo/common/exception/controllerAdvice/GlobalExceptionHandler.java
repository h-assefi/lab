package com.irisaco.common.exception.controllerAdvice;

import com.irisaco.common.exception.exceptionFormat.ExceptionMessage;
import com.irisaco.common.exception.exceptionTypes.*;
import com.irisaco.common.logger.IrisaLogger;
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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final IrisaLogger irisaLogger;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.servlet.context-path:}")
    private String context;

    public GlobalExceptionHandler(IrisaLogger irisaLogger) {
        this.irisaLogger = irisaLogger;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ExceptionMessage> customException(CustomException ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(RepositoryException.class)
    public final ResponseEntity<ExceptionMessage> repositoryException(RepositoryException ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionMessage> badRequestException(BadRequestException ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> result = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            result.put(field, error.getDefaultMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public final ResponseEntity<ExceptionMessage> unAuthorizedException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> accessDeniedException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(logAndGetExceptionMessage(ex, webRequest), HttpStatus.FORBIDDEN);
    }

    private ExceptionMessage logAndGetExceptionMessage(Exception ex, WebRequest webRequest) {
        logException(ex);
        return getExceptionMessage(ex, webRequest);
    }

    private void logException(Exception ex) {
        irisaLogger.error(ex.getMessage());
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