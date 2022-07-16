package com.example.javeke.ws.ace.models.dto;

import java.util.ArrayList;

public class DeviceInfoDto {
    private String id;
    private String name;
    private boolean enabled;
    private String healthStatus;
    private String type;

    public DeviceInfoDto() { }

    public DeviceInfoDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public String toString() {
        return "DeviceDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", healthStatus='" + healthStatus + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
