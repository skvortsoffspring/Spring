package com.example.skvortsoff.controller;

import com.example.skvortsoff.aop.LogAnnotation;
import com.example.skvortsoff.dto.AuthUserDto;
import com.example.skvortsoff.dto.UserNewDto;
import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.exeption.TokenException;
import com.example.skvortsoff.repository.UserRepository;
import com.example.skvortsoff.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
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
    private final  UserRepository userRepository;
    public AuthenticationRestController(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logging success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthUserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Error login or password",
                    content = @Content)})

    @ResponseBody
    @PostMapping("login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthUserDto request) throws TokenException {
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

    @GetMapping("get-user/{email}")
    @ResponseBody
    public User getUser(@PathVariable String email) {
        return userRepository.findByEmail(email).orElse(new User());
    }

    @GetMapping("get-exist/{email}")
    @ResponseBody
    public ResponseEntity<?> getUserExist(@PathVariable String email) {
       if(userRepository.findByEmail(email).orElse(null) != null)
           return new ResponseEntity<>(HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
       else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
