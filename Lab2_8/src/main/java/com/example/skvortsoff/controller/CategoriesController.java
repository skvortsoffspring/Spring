package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.CategoryDto;
import com.example.skvortsoff.dto.CategoryDtoNew;
import com.example.skvortsoff.dto.CategoryIdDto;
import com.example.skvortsoff.dto.CategoryMinDto;
import com.example.skvortsoff.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories/**")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("names")
    @ResponseBody
    public Iterable<CategoryMinDto> GetNames(){
        return categoriesService.GetCategoriesNames();
    }

    @GetMapping("full")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public Iterable<CategoryDto> GetCategoriesFull(){
        return categoriesService.GetCategories();
    }

    @GetMapping("admin/info/{name}")
    @ResponseBody
    //@PreAuthorize("hasAuthority('user:write')")
    public CategoryMinDto GetCategories(@PathVariable String name){
        return categoriesService.GetCategoriesMinInfo(name);
    }

    @PostMapping("admin/add")
    @ResponseBody
    //@PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> AddCategory(@Valid @RequestBody CategoryDtoNew category){
        return categoriesService.AddCategory(category);
    }

    @PutMapping("admin/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> UpdateCategory(@Valid @RequestBody CategoryDto category){
        return categoriesService.UpdateCategory(category);
    }

    @DeleteMapping("admin/del")
    @ResponseBody
    //@PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> DeleteCategory(@RequestBody CategoryIdDto categoryId){
        return categoriesService.DeleteCategory(categoryId);
    }
}