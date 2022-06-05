package com.example.javeke.ws.ace.models;

import com.example.javeke.ws.ace.models.dto.DeviceData;

public class SocketDataMessage {
    private DeviceData data;
    private String message;

    public SocketDataMessage(DeviceData data, String message) {
        this.data = data;
        this.message = message;
    }

    public DeviceData getData() {
        return data;
    }

    public void setData(DeviceData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
