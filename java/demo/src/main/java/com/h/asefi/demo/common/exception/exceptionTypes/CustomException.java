package com.h.asefi.demo.common.exception.exceptionTypes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CustomException extends RuntimeException {


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Exception exception) {
        super(message, exception);
    }
}
