package com.example.skvortsoff.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailSenderException extends Exception {

    public EmailSenderException() {
    }

    public EmailSenderException(String message) {
        super(message);
    }
}
