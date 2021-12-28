package com.example.skvortsoff.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String getRegisterPage(){
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('user:read')")
    public String GetAdminPage(){
        return "admin";
    }
}
