package com.example.javeke.services.portfolio.models.response;

public class PortfolioVisitorResponse {
    private String ipAddress;
    private int count;

    public PortfolioVisitorResponse(){ }

    public PortfolioVisitorResponse(String ipAddress, int count) {
        this.ipAddress = ipAddress;
        this.count = count;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PortfolioVisitorResponse{" +
                "ipAddress='" + ipAddress + '\'' +
                ", count=" + count +
                '}';
    }
}