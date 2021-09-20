package com.example.proj.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private String name;
    private String director;
    private String genre;
    private Integer year;
}
