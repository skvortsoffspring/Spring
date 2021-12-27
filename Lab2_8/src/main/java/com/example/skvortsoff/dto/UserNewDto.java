package com.example.skvortsoff.dto;

import com.example.skvortsoff.validator.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNewDto {

    @NotBlank
    @Pattern(regexp = "^[\\w-]{3,20}[0-9a-zA-Z]$", message = "Only latin alphabet and number")
    public String login;

    @NotBlank
    @Phone
    public String phone;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;

}
