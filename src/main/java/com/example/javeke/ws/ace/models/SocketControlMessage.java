package com.example.javeke.ws.ace.models;

import com.example.javeke.ws.ace.models.dto.DeviceInfoDto;

public class SocketControlMessage {
    private DeviceInfoDto control;
    private String message;

    public SocketControlMessage(){}

    public SocketControlMessage(DeviceInfoDto control, String message) {
        this.control = control;
        this.message = message;
    }

    public DeviceInfoDto getControl() {
        return control;
    }

    public void setControl(DeviceInfoDto control) {
        this.control = control;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
