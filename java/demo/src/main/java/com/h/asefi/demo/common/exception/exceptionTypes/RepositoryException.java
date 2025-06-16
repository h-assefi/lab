package com.h.asefi.demo.common.exception.exceptionTypes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class RepositoryException extends RuntimeException {


    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
