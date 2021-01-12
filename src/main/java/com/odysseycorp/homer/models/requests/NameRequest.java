package com.odysseycorp.homer.models.requests;

/**
 * To update name only on controller creation.
 */
public class NameRequest {

    private String name;

    public NameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
