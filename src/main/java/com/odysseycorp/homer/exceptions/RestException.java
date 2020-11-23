package com.odysseycorp.homer.exceptions;

/**
 * Exception for the server. All custom exceptions extends of it.
 */
public class RestException extends RuntimeException {

    private int httpStatus;
    private String detailedMessage;

    public RestException(int httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }

    public RestException(int httpStatus, String message, String detailedMessage){
        this(httpStatus, message);
        this.detailedMessage = detailedMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }
}
