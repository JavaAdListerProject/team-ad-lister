package com.codeup.adlister.models;

public class Response {

    boolean success = false;

    public Response() {}

    public Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

