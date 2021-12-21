package com.example.skvortsoff.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @NotBlank
    @Pattern(regexp = "^[\\w-]{3,20}[0-9a-zA-Z]$", message = "Only latin alphabet and number")
    public String login;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;


}
