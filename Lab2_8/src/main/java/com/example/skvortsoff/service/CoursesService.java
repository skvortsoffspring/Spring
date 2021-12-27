package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.CourseDto;
import com.example.skvortsoff.repository.CourseRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursesService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Iterable<CourseDto> getPage(long id){
        return Mapper.mapAll(courseRepository.findNineCourses(id - 1), CourseDto.class);
    }

    public CourseDto GetCourseDto(long id){
        return Mapper.map(courseRepository.getById(id), CourseDto.class);
    }

    public Iterable<CourseDto> Search(String name){
        return Mapper.mapAll(courseRepository.findByNameContainingIgnoreCase(name), CourseDto.class);
    }

    public long Size(){
        return courseRepository.count();
    }
}
