package com.example.javeke.ws.ace.models.dao;

import com.example.javeke.ws.ace.models.dto.DeviceDto;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;

@Document("organization")
public class Organization {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String organizationId;
    private String name;
    private String description;
    private ArrayList<DeviceDto> devices;

    public Organization() {
    }

    public Organization(String id, String organizationId, String name, String description) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
<<<<<<< HEAD
        this.devices = new ArrayList<>();
=======
>>>>>>> d0ad902 (implement websocket API and REST API for the organization CRUD operations)
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

