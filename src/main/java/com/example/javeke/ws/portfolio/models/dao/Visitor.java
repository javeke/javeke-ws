package com.example.javeke.ws.portfolio.models.dao;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("visitors")
public class Visitor {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String city;
    private String state;
    private String country;

    private String ipAddress;
    private int numVisits;

    public Visitor(){ }


    public Visitor(String id, String city, String state, String country, String ipAddress) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.ipAddress = ipAddress;
        this.numVisits = 0;
    }

    public Visitor(String id, String city, String state, String country, String ipAddress, int numVisits) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.ipAddress = ipAddress;
        this.numVisits = numVisits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getNumVisits() {
        return numVisits;
    }

    public void setNumVisits(int numVisits) {
        this.numVisits = numVisits;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", numVisits=" + numVisits +
                '}';
    }
}
