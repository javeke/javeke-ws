package com.example.javeke.ws.portfolio.models.requests;

public class VisitorRequest {

    private String city;
    private String state;
    private String country;
    private String ipAddress;

    public VisitorRequest(String city, String state, String country, String ipAddress) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.ipAddress = ipAddress;
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

    @Override
    public String toString() {
        return "VisitorRequest{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
