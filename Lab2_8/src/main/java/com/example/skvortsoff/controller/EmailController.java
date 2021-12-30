package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.EmailFormDto;
import com.example.skvortsoff.exeption.EmailSenderException;
import com.example.skvortsoff.service.EmailSender;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Operation(summary = "Sender email for feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmailFormDto.class)) }),
            @ApiResponse(responseCode = "504", description = "Timeout await sending message",
                    content = @Content)})
    @PostMapping(value = "/send")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> sendSimpleEmail(@Valid @RequestBody EmailFormDto formEmail) throws EmailSenderException {

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