package com.example.skvortsoff.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchased")
public class Purchased {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COURSE")
    private Course course;

    @Column(name = "`KEY`", length = 25)
    private String key;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "`DATE ACTIVATED`")
    private LocalDate dateActivated;

    @Column(name = "`HARDWARE TYPE`", length = 20)
    private String hardwareType;

    @Column(name = "`HARDWARE SERIAL`", length = 50)
    private String hardwareSerial;
}