package com.widua.videorest.models;

import org.springframework.http.HttpStatus;

public class ResponseBodyModel {

    private String message;
    private HttpStatus status;
    private Object object;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ResponseBodyModel(String message, HttpStatus status, Object body) {
        this.message = message;
        this.status = status;
        this.object = body;
    }

    public ResponseBodyModel(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
