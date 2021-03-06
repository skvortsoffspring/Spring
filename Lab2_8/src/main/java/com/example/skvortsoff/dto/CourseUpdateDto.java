package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDto {

    @Digits(integer=18,fraction=0)
    private long id;

    @NotBlank
    private String name;

    @Null
    private String description;

    @NotNull
    private Category category;

    @Null
    private byte[] image;

    private BigDecimal price;

    private Integer complexity;
}
