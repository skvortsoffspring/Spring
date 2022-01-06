package com.example.skvortsoff.controller;

import com.example.skvortsoff.aop.LogAnnotation;
import com.example.skvortsoff.dto.*;
import com.example.skvortsoff.service.CoursesService;
import com.example.skvortsoff.util.Mapper;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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

    @LogAnnotation
    @Before("Create course")
    @PostMapping("admin/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> AddCategory(@Valid @RequestBody CourseNewDto courseNewDto) throws IOException {
        return coursesService.AddCourse(courseNewDto);
    }

    @PutMapping("admin/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> UpdateCategory(@Valid @RequestBody CourseUpdateDto courseUpdateDto){
        return coursesService.UpdateCourse(courseUpdateDto);
    }

    @DeleteMapping("admin/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> DeleteCategory(@Valid @RequestBody CourseIdDto courseIdDto){
        return coursesService.DeleteCourse(courseIdDto);
    }

}