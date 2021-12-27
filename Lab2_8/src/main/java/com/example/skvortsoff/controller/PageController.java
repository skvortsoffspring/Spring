package com.example.skvortsoff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @GetMapping("/admin")
    public String GetAdminPage(){
        return "admin";
    }
}
