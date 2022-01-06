package com.example.skvortsoff.dto;

import com.example.skvortsoff.entity.Course;
import com.example.skvortsoff.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPurchasedDto {

    @NotNull
    private User user;

    @NotNull
    private Course course;

    private String key;

    private Boolean active;

    private LocalDate dateActivated;

    private String hardwareType;

    private String hardwareSerial;
}
