package com.example.skvortsoff.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "purchased", indexes = {
        @Index(name = "USER", columnList = "USER"),
        @Index(name = "COURSE", columnList = "COURSE")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COURSE")
    private Course course;

    @Column(name = "`KEY`", nullable = false, length = 25)
    private String key;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "`DATE ACTIVATED`")
    private LocalDate dateActivated;

    @Column(name = "`HARDWARE TYPE`", nullable = false, length = 20)
    private String hardwareType;

    @Column(name = "`HARDWARE SERIAL`", nullable = false, length = 50)
    private String hardwareSerial;
}