package com.odysseycorp.homer.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "controller", schema = "iot", catalog = "")
public class Controller {

    private String id;
    private String ip;
    private String name;
    private boolean usePresenceSensor;
    private boolean useTemperatureSensor;
    private boolean alarmIsEnabled;
    private double maxTemperature;
    private double minTemperature;
    private double maxHumidity;
    private double minHumidity;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "idController")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ip", nullable = false)
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public boolean isUsePresenceSensor() {
        return usePresenceSensor;
    }

    public void setUsePresenceSensor(boolean usePresenceSensor) {
        this.usePresenceSensor = usePresenceSensor;
    }

    @Transient
    public boolean isUseTemperatureSensor() {
        return useTemperatureSensor;
    }

    public void setUseTemperatureSensor(boolean useTemperatureSensor) {
        this.useTemperatureSensor = useTemperatureSensor;
    }

    @Transient
    public boolean isAlarmIsEnabled() {
        return alarmIsEnabled;
    }

    public void setAlarmIsEnabled(boolean alarmIsEnabled) {
        this.alarmIsEnabled = alarmIsEnabled;
    }

    @Transient
    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Transient
    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Transient
    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    @Transient
    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }
}
