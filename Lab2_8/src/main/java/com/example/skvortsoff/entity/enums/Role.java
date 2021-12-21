package com.example.skvortsoff.entity.enums;

public enum Role {
    USER(0),
    ADMIN(1);

    public Integer LevelAccess;
    Role(Integer levelAccess)
    {
        this.LevelAccess = levelAccess;
    }
}
