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
    private Long id;

    @Column(name = "LOGIN", nullable = false, length = 20)
    private String login;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PHONE", length = 15)
    private String phone;

    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;

    @Column(name = "ROLE")
    private Integer role;
}