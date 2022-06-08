package com.example.javeke.ws.ace.models;

import com.example.javeke.ws.ace.models.dto.DeviceDto;

public class SocketControlMessage {
    private DeviceDto control;
    private String message;

    public SocketControlMessage(){}

    public SocketControlMessage(DeviceDto control, String message) {
        this.control = control;
        this.message = message;
    }

    public DeviceDto getControl() {
        return control;
    }

    public void setControl(DeviceDto control) {
        this.control = control;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
