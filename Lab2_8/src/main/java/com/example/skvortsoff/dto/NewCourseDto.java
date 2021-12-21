package com.example.skvortsoff.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class NewCourseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private byte[] image;

    @NotBlank
    private BigDecimal price;

    @NotNull
    private Boolean hide;

    @Column(name = "COMPLEXITY")
    private Integer complexity;
}
