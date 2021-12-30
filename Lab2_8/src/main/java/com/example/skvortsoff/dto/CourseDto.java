package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    @NotNull
    private Long id;

    @Null
    private String name;

    private Category category;

    @Null
    private byte[] image;

    @Null
    private BigDecimal price;

    @Null
    private Integer complexity;

}
