package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.NewCourseDto;
import com.example.skvortsoff.entity.Course;
import com.example.skvortsoff.repository.CourseRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/**")
public class CoursesController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("get/page/{page}")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseBody
    public Iterable<Course> getAllCourses(@PathVariable("page") int id){
        return courseRepository.findNineCourses(id);
    }

    @GetMapping("get/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public NewCourseDto Create(@PathVariable("id") long id){
        return Mapper.map(courseRepository.getById(id), NewCourseDto.class);
    }

    @PostMapping("get/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public NewCourseDto CreatePost(@PathVariable("id") long id){
        return Mapper.map(courseRepository.getById(id), NewCourseDto.class);
    }

}