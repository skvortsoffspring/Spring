package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.EmailFormDto;
import com.example.skvortsoff.exeption.EmailSenderException;
import com.example.skvortsoff.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/email")
public class EmailController {

    private final EmailSender emailService;

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    public EmailController(EmailSender emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> sendSimpleEmail(@Validated @RequestBody EmailFormDto formEmail) throws EmailSenderException {

        StringBuilder builder = new StringBuilder();
        builder.append(formEmail.getFrom()).append("(").append(formEmail.getEmail()).append(")");
        try {
            emailService.sendSimpleEmail(email, builder.toString(), formEmail.getMessage());
        } catch (MailException mailException) {
            throw  new EmailSenderException(mailException.getMessage());
        }

        return new ResponseEntity<>("Message send successful", HttpStatus.OK);
    }
}