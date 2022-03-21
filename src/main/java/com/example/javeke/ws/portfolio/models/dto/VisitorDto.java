package com.example.javeke.ws.portfolio.models.dto;

import com.example.javeke.ws.portfolio.models.dao.Visitor;

public class VisitorDto {

    private String city;
    private String state;
    private String country;
    private String ipAddress;
    private int numVisits;

    public VisitorDto(){ }

    public VisitorDto(String city, String state, String country, String ipAddress, int numVisits) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.ipAddress = ipAddress;
        this.numVisits = numVisits;
    }

    public VisitorDto(Visitor visitor) {
        this.city = visitor.getCity();
        this.state = visitor.getState();
        this.country = visitor.getCountry();
        this.ipAddress = visitor.getIpAddress();
        this.numVisits = visitor.getNumVisits();
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
        return "VisitorDto{" +
                "ipAddress='" + ipAddress + '\'' +
                ", numVisits=" + numVisits +
                '}';
    }
}