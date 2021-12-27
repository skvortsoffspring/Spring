package com.example.skvortsoff.exeption;

import lombok.Getter;

@Getter
public class ExceptionNotFoundUser extends Exception{
    private String errorMessage;

    public ExceptionNotFoundUser(String message) {
        this.errorMessage = message;
    }

    public ExceptionNotFoundUser(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }
}
