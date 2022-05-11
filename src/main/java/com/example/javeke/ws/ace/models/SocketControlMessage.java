package com.example.javeke.ws.ace.models;

public class SocketControlMessage {
    private String control;
    private String message;

    public SocketControlMessage(String control, String message) {
        this.control = control;
        this.message = message;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
