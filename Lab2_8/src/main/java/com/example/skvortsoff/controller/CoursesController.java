package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.AuthUserDto;
import com.example.skvortsoff.entity.Course;
import com.example.skvortsoff.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/courses/**")
public class CoursesController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("get/{page}")
    @ResponseBody
    public Iterable<Course> getAllCourses(@PathVariable("page") int page){

        return courseRepository.findNineCourses(page);
    }

    @GetMapping("getone/{id}")
    public AuthUserDto Create(@PathVariable("id") long id){
        return new AuthUserDto();
    }

}