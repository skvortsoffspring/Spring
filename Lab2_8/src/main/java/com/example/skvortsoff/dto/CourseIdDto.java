package com.example.skvortsoff.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CourseIdDto {
    @NotBlank
    private Long id;
}
