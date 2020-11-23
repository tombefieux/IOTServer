package com.odysseycorp.homer.exceptions;

import org.springframework.http.HttpStatus;

/**
 * When a bad argument is given to controllers.
 */
public class BadArgumentException extends RestException {

    public BadArgumentException() {
        super(HttpStatus.BAD_REQUEST.value(), "Bad request");
    }

    public BadArgumentException(String detailedMessage) {
        super(HttpStatus.BAD_REQUEST.value(), "Bad request", detailedMessage);
    }
}
