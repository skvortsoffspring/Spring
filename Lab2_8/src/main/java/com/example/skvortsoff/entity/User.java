package com.example.skvortsoff.entity;

import com.example.skvortsoff.entity.enums.Role;
import com.example.skvortsoff.entity.enums.Status;
import com.example.skvortsoff.validator.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

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

    @Email(message = "Email invalid")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Phone
    @Column(name = "PHONE", length = 15)
    private String phone;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private Status status;
}