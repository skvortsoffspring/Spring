package com.example.skvortsoff.controller;

import com.example.skvortsoff.dto.CategoryDto;
import com.example.skvortsoff.dto.CategoryNameDto;
import com.example.skvortsoff.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Iterable<CategoryNameDto> GetNames(){
        return categoriesService.GetCategoriesNames();
    }

    @GetMapping("full")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:read')")
    public Iterable<CategoryDto> GetCategories(){
        return categoriesService.GetCategories();
    }

}
