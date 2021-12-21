package com.example.skvortsoff.controller;

import com.example.skvortsoff.entity.Course;
import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.repository.CourseRepository;
import com.example.skvortsoff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test")
    @ResponseBody
    public Iterable<User> getTest(@RequestParam(defaultValue = "main") String client) {

        return userRepository.findAll();
    }

    @GetMapping("/GetAllCourses")
    public Iterable<Course> getAllCourses(Model model){

        return courseRepository.findTenCourses(1);
    }
}