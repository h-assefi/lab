package com.h.asefi.demo.common.exception.exceptionTypes;

public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Exception exception) {
        super(message, exception);
    }
}
