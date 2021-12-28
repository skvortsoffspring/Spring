package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.CourseDto;
import com.example.skvortsoff.entity.Category;
import com.example.skvortsoff.repository.CategoryRepository;
import com.example.skvortsoff.repository.CourseRepository;
import com.example.skvortsoff.util.Mapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CoursesService(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }



    public Iterable<CourseDto> getPage(long id, String categoryName){
        Iterable<CourseDto> result;
        if(!categoryName.equals("empty")){
            Category category = categoryRepository.findByName(categoryName);
            return Mapper.mapAll(courseRepository.findNineCoursesByCourses(id - 1, category.getId()), CourseDto.class);
        }
        return Mapper.mapAll(courseRepository.findNineCourses(id - 1), CourseDto.class);
    }

    public Iterable<CourseDto> getCoursesByCategory(String categoryName){
        Category category;
        try{
        category = categoryRepository.findByName(categoryName);
        }catch (Exception e){
            throw new ServiceException("Exception CoursesService.getCoursesByCategory");
        }
        return Mapper.mapAll(courseRepository.findByCategory(category.getId()), CourseDto.class);
    }

    public CourseDto GetCourseDto(long id){
        return Mapper.map(courseRepository.getById(id), CourseDto.class);
    }

    public Iterable<CourseDto> Search(String name){
        return Mapper.mapAll(courseRepository.findByNameContainingIgnoreCase(name), CourseDto.class);
    }

    public long getSize(long id, String categoryName){
        try{
            if(!categoryName.equals("empty")){
                return courseRepository.countByCategory(categoryRepository.findByName(categoryName));
            }
            return courseRepository.count();
        }catch (Exception e){
            throw new ServiceException("Exception CoursesService.getSize");
        }
    }
}
