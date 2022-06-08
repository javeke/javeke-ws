package com.example.javeke.ws.ace.util;

public class ActionResponse<T> {
    private boolean isSuccessful;
    private T data;

    public ActionResponse(){}

    public ActionResponse(boolean isSuccessful, T data) {
        this.isSuccessful = isSuccessful;
        this.data = data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        this.isSuccessful = successful;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
