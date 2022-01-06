package com.example.skvortsoff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
