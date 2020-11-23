package com.odysseycorp.homer.models.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;

/**
 * To parse a custom server exception in JSON.
 */
public class ExceptionModel {

    private int httpStatus;
    private long timestamp;
    private String detailedMessage;
    private String message;

    public ExceptionModel(int httpStatus, String message, String detailedMessage) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.timestamp = System.currentTimeMillis();
        if(detailedMessage == null) {
            detailedMessage = "";
        }
    }

    /**
     * Gets the JSON of the exception.
     *
     * @return the JSON of the exception.
     * @throws JsonProcessingException
     */
    public String getJson() {
        final JsonObject result = new JsonObject();
        result.addProperty("httpStatus", httpStatus);
        result.addProperty("timestamp", timestamp);
        result.addProperty("message", this.message);
        result.addProperty("detailedMessage", detailedMessage);
        return result.toString();
    }
}