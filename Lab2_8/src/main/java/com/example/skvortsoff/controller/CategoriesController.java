package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.CategoryDto;
import com.example.skvortsoff.dto.CategoryMinDto;
import com.example.skvortsoff.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('user:write')")
    public CategoryMinDto GetCategories(@PathVariable String name){
        return categoriesService.GetCategoriesMinInfo(name);
    }

    @PostMapping("admin/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> AddCategory(@RequestBody CategoryDto category){
        return categoriesService.AddCategory(category);
    }

    @PutMapping("admin/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> UpdateCategory(@RequestBody CategoryDto category){
        return categoriesService.UpdateCategory(category);
    }

    @DeleteMapping("admin/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> DeleteCategory(@RequestBody CategoryDto category){
        return categoriesService.DeleteCategory(category);
    }
}