package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.AuthUserDto;
import com.example.skvortsoff.dto.UserNewDto;
import com.example.skvortsoff.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationRestController {


    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseBody
    @PostMapping("login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthUserDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserNewDto userDto) {
        return authenticationService.register(userDto);
    }
}
