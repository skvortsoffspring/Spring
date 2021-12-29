package com.example.skvortsoff.service;

import com.example.skvortsoff.dto.CategoryDto;
import com.example.skvortsoff.dto.CategoryMinDto;
import com.example.skvortsoff.entity.Category;
import com.example.skvortsoff.repository.CategoryRepository;
import com.example.skvortsoff.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<CategoryMinDto> GetCategoriesNames(){
        return Mapper.mapAll(categoryRepository.findAll(), CategoryMinDto.class);
    }

    public Iterable<CategoryDto> GetCategories(){
        return Mapper.mapAll(categoryRepository.findAll(), CategoryDto.class);
    }

    public CategoryMinDto GetCategoriesMinInfo(String name){
        return Mapper.map(categoryRepository.findByName(name), CategoryMinDto.class);
    }

    public ResponseEntity<?> AddCategory(CategoryDto categoryDto){
        if(!categoryRepository.existsByName(categoryDto.getName()))
        {
            categoryRepository.save(Mapper.map(categoryDto, Category.class));
            return ResponseEntity.ok("Category add");
        }
        return ResponseEntity.ok("Category exists");
    }


    public ResponseEntity<?> UpdateCategory(CategoryDto categoryDto){
        if(categoryRepository.existsById(categoryDto.getId()))
        {
            Category category = categoryRepository.getById(categoryDto.getId());
            category.setName(categoryDto.getName());
            category.setImage(categoryDto.getImage());
            categoryRepository.save(category);
            return ResponseEntity.ok("Category update");
        }
        return ResponseEntity.ok("Error update");
    }

    public ResponseEntity<?> DeleteCategory(CategoryDto categoryDto){
        if(categoryRepository.existsById(categoryDto.getId()))
        {
            categoryRepository.deleteById(categoryDto.getId());
            return ResponseEntity.ok("Category delete");
        }
        return ResponseEntity.ok("Error delete category");
    }

}
