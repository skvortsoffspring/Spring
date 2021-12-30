package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDto {

    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private byte[] image;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private Integer complexity;
}
