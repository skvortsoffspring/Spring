package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.CategoryDto;
import com.example.skvortsoff.dto.CategoryNameDto;
import com.example.skvortsoff.entity.Category;
import com.example.skvortsoff.repository.CategoryRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<CategoryNameDto> GetCategoriesNames(){
        return Mapper.mapAll(categoryRepository.findAll(), CategoryNameDto.class);
    }

    public Iterable<CategoryDto> GetCategories(){
        return Mapper.mapAll(categoryRepository.findAll(), CategoryDto.class);
    }

}
