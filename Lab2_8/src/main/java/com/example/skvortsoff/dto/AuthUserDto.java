package com.example.skvortsoff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
