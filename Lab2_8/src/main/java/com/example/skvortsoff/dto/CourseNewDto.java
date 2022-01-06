package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseNewDto {

    @NotBlank
    private String name;
    @Size(min = 3, max = 200, message = "About Me must be between 10 and 100 characters")

    @Null
    @Size(min = 10, max = 200, message = "About Me must be between 20 and 1000 characters")
    private String description;

    @NotNull
    private Category category;

    private byte[] image;

    @Digits(integer=5,fraction=0)
    private BigDecimal price;

    @Max(10)
    @Min(1)
    private Integer complexity;
}
