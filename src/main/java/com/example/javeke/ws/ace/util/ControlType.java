package com.example.javeke.ws.ace.util;

public enum ControlType {
    StateChange("StateChange");

    private final String controlType;

    ControlType(String controlType) {
        this.controlType = controlType;
    }

    @Override
    public String toString() {
        return controlType;
    }
}
