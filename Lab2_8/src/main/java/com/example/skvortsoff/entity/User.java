package com.example.skvortsoff.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "user", indexes = {
        @Index(name = "IX_USER_EMAIL", columnList = "EMAIL")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;

    @Column(name = "LANGUAGE", length = 2)
    private String language;

    @Column(name = "ROLE")
    private Integer role;
}