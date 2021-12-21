package com.example.skvortsoff.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
@Table(name = "course", indexes = {
        @Index(name = "CATEGORY", columnList = "CATEGORY"),
        @Index(name = "IX_COURSE_NAME", columnList = "NAME"),
        @Index(name = "AUTHOR", columnList = "AUTHOR")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CATEGORY")
    private Category category;

    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    private String description;

    @Column(name = "IMAGE", columnDefinition = "blob")
    private byte[] image;

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "HIDE")
    private Boolean hide;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;

    @Column(name = "COMPLEXITY")
    private Integer complexity;

}