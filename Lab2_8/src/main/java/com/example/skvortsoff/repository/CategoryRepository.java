package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Category;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    @Query("select c from Category c where c.name = ?1")
    Category findByName(String name);
}
