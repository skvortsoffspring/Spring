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

    @GetMapping("get/page/{page}/{category}")
    @ResponseBody
    public Iterable<CourseDto> getAllCoursesPage(@PathVariable("page") long id,@PathVariable("category") String category){
        return coursesService.getPage(id, category);
    }

    @GetMapping("get/category/{category}")
    @ResponseBody
    public Iterable<CourseDto> getCoursesByCategory(@PathVariable String category){
        return coursesService.getCoursesByCategory(category);
    }

    @GetMapping("get/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public CourseDto CreateCourseById(@PathVariable("id") long id){
        return coursesService.GetCourseDto(id);
    }

    @GetMapping("get/search/{val}")
    @ResponseBody
    public Iterable<CourseDto> getByNameSearch(@PathVariable("val") String name){

        return coursesService.Search(name);
    }

    @GetMapping("get/size/{page}/{category}")
    @ResponseBody
    public long getSize(@PathVariable("page") long id,@PathVariable("category") String category){
        return coursesService.getSize(id, category);
    }

}