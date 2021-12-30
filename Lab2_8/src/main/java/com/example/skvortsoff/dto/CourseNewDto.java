package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseNewDto {

    @NotBlank
    private String name;

    @Null
    private String description;

    @NotNull
    private Category category;

    private byte[] image;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private Integer complexity;
}
