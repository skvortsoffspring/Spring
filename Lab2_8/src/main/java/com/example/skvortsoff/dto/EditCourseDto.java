package com.example.skvortsoff.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EditCourseDto {

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
