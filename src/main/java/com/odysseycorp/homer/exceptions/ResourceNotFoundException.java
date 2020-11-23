package com.odysseycorp.homer.exceptions;

import org.springframework.http.HttpStatus;

/**
 * To throw when a resource can't be found.
 */
public class ResourceNotFoundException extends RestException {

    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Resource not found");
    }

    public ResourceNotFoundException(String detailedMessage) {
        super(HttpStatus.NOT_FOUND.value(), "Resource not found", detailedMessage);
    }
}