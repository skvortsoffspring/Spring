package com.example.skvortsoff.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TokenException extends Exception{

    public TokenException() {
    }
    public TokenException(String message) {
        super(message);
    }
}
