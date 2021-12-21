package com.example.skvortsoff.entity.enums;

import lombok.Getter;

@Getter
public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    Permission(String permision) {
        this.permission = permision;
    }
}
