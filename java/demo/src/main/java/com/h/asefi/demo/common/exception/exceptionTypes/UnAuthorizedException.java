package com.h.asefi.demo.common.exception.exceptionTypes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Exception exception) {
        super(message, exception);
    }
}
