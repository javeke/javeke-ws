package com.example.javeke.ws.ace.models.dto;

import com.example.javeke.ws.ace.models.dao.Organization;

import java.util.ArrayList;

public class OrganizationDto {
    private String organizationId;
    private String name;
    private String description;
    private ArrayList<DeviceDto> devices;

    public OrganizationDto() {
    }

    public OrganizationDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public OrganizationDto(String organizationId, String name, String description) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.devices = new ArrayList<>();
    }

    public OrganizationDto(String organizationId, String name, String description, ArrayList<DeviceDto> devices) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.devices = devices;
    }

    public OrganizationDto(Organization organization) {
        this.organizationId = organization.getOrganizationId();
        this.name = organization.getName();
        this.description = organization.getDescription();
        this.devices = organization.getDevices();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<DeviceDto> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<DeviceDto> devices) {
        this.devices = devices;
    }
}
