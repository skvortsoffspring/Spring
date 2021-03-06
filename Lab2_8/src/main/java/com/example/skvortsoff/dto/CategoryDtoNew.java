package com.example.skvortsoff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoNew {

    @NotBlank
    @Size(min = 1, max = 30, message = "About Me must be between 1 and 30 characters")
    private String name;

    @Null
    private byte[] image;


}
