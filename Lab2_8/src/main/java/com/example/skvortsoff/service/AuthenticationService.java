package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.AuthUserDto;
import com.example.skvortsoff.dto.UserNewDto;
import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.entity.enums.Role;
import com.example.skvortsoff.entity.enums.Status;
import com.example.skvortsoff.exeption.TokenException;
import com.example.skvortsoff.repository.UserRepository;
import com.example.skvortsoff.security.JwtTokenProvider;
import com.example.skvortsoff.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<?> authenticate(AuthUserDto request) throws TokenException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail((request.getEmail())).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            /*Map<Object, Object> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);*/
            throw new TokenException("User or Email not valid");
        }
    }
    public ResponseEntity<?> register(UserNewDto userDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        userDto.password = passwordEncoder.encode(userDto.getPassword());
        var user = Mapper.map(userDto, User.class);
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        Map<String, String> map = new HashMap<>();
        map.put("message", "User registered");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
