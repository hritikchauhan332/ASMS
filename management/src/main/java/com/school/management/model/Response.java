package com.school.management.model;

import org.springframework.http.HttpStatus;

public class Response {
    private Integer status;
    private String message;
    private Object data;

    public Response(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}
