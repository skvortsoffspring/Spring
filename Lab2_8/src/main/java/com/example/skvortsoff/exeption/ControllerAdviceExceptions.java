package com.example.skvortsoff.exeption;

import com.example.skvortsoff.model.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdviceExceptions {

    @ResponseBody
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<CustomResponse> handleExceptionService(TokenException e) {
        return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<CustomResponse> handleExceptionAuthenticate(JwtAuthenticationException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        CustomResponse response = new CustomResponse(message);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomResponse> handleExceptionAuthenticate(UsernameNotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        CustomResponse response = new CustomResponse(message);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(EmailSenderException.class)
    public ResponseEntity<CustomResponse> handleExceptionSenderEmail(EmailSenderException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        CustomResponse response = new CustomResponse(message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}