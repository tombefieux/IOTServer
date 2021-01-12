package com.odysseycorp.homer.models.responses;

/**
 * Sensor response from controller
 */
public class SensorsResponse {

    private Double temperature;
    private Double humidity;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
