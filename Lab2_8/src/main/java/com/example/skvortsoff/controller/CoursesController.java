package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.CourseDto;
import com.example.skvortsoff.service.CoursesService;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/**")
public class CoursesController {

    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }


    @GetMapping("get/page/{page}")
    @ResponseBody
    public Iterable<CourseDto> getAllCoursesPage(@PathVariable("page") long id){
        return coursesService.getPage(id);
    }

    @GetMapping("get/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public CourseDto CreateCourseById(@PathVariable("id") long id){
        return coursesService.GetCourseDto(id);
    }

    @GetMapping("get/search/{val}")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseBody
    public Iterable<CourseDto> getByNameSearch(@PathVariable("val") String name){

        return coursesService.Search(name);
    }

    @PostMapping("get/size")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public long getSize(){
        return coursesService.Size();
    }

}