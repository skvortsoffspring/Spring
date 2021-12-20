package com.example.skvortsoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.SQLException;

@SpringBootApplication
public class SkvortsoffApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkvortsoffApplication.class, args);
    }
}