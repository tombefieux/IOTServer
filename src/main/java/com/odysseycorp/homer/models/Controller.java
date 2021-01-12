package com.odysseycorp.homer.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "controller", schema = "iot", catalog = "")
public class Controller {

    private String id;
    private String ip;
    private String name;
    private Boolean usePresenceSensor;
    private Boolean useTemperatureSensor;
    private Boolean alarmIsEnabled;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer maxHumidity;
    private Integer minHumidity;

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
    public Boolean isUsePresenceSensor() {
        return usePresenceSensor;
    }

    public void setUsePresenceSensor(Boolean usePresenceSensor) {
        this.usePresenceSensor = usePresenceSensor;
    }

    @Transient
    public Boolean isUseTemperatureSensor() {
        return useTemperatureSensor;
    }

    public void setUseTemperatureSensor(Boolean useTemperatureSensor) {
        this.useTemperatureSensor = useTemperatureSensor;
    }

    @Transient
    public Boolean isAlarmIsEnabled() {
        return alarmIsEnabled;
    }

    public void setAlarmIsEnabled(Boolean alarmIsEnabled) {
        this.alarmIsEnabled = alarmIsEnabled;
    }

    @Transient
    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Integer maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Transient
    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Integer minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Transient
    public Integer getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(Integer maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    @Transient
    public Integer getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(Integer minHumidity) {
        this.minHumidity = minHumidity;
    }
}
