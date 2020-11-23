package com.odysseycorp.homer.spring.handlers;

import com.odysseycorp.homer.exceptions.ResourceNotFoundException;
import com.odysseycorp.homer.exceptions.RestException;
import com.odysseycorp.homer.models.exceptions.ExceptionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Exception handler to catch our custom exceptions.
 */
@ControllerAdvice
public class ServerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestException.class})
    public final ResponseEntity<?> handleUserNotFoundException(RestException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).contentType(MediaType.APPLICATION_JSON).body(
                new ExceptionModel(ex.getHttpStatus(), ex.getMessage(), ex.getDetailedMessage())
                        .getJson());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public final ResponseEntity<?> handleUserNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ResourceNotFoundException toReturn = new ResourceNotFoundException(ex.getMessage());
        return ResponseEntity.status(toReturn.getHttpStatus()).contentType(MediaType.APPLICATION_JSON).body(
                new ExceptionModel(toReturn.getHttpStatus(), toReturn.getMessage(), toReturn.getDetailedMessage())
                        .getJson());
    }
}