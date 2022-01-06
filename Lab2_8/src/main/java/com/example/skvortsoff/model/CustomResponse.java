package com.example.skvortsoff.model;

import lombok.Data;

@Data
public class CustomResponse {

    private String message;
    private String error;

    public CustomResponse(String message) {
        this.message = message;
    }

    public CustomResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}