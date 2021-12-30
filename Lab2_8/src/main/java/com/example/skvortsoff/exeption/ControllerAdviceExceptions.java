package com.example.skvortsoff.exeption;

import com.example.skvortsoff.model.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
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
    @ExceptionHandler({AuthenticationException.class, MissingCsrfTokenException.class, InvalidCsrfTokenException.class, SessionAuthenticationException.class})
    public ResponseEntity<CustomResponse> handleExceptionAuthenticate(RuntimeException e) {
        String accessDenied = "Access denied";
        CustomResponse response = new CustomResponse(e.getMessage(), accessDenied);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomResponse> handleExceptionAuthenticate(UsernameNotFoundException e) {
        String notFoundUser = "Not found user";
        CustomResponse response = new CustomResponse(e.getMessage(), notFoundUser);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(EmailSenderException.class)
    public ResponseEntity<CustomResponse> handleExceptionSenderEmail(EmailSenderException e) {
        String errorSendEmail = "Error send email";
        CustomResponse response = new CustomResponse( e.getMessage(), errorSendEmail);
        return new ResponseEntity<>(response, HttpStatus.GATEWAY_TIMEOUT);
    }
}