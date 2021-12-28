package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Category;
import com.example.skvortsoff.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
     @Query(value = "select count(*) from Course c where c.category = ?1", nativeQuery = true)
     long countByCategory(Category category);

     @Query(value = "select * from Course c where c.category = ?1", nativeQuery = true)
     List<Course> findByCategory(Long categoryID);

     @Query(value = "select * from Course c where upper(c.name) like upper(concat('%', ?1, '%'))", nativeQuery = true)
     Collection<Course> findByNameContainingIgnoreCase( String name);

     @Query(value = "CALL FINDEIGHTCOURSES(:page);", nativeQuery = true)
     Collection<Course> findNineCourses(@Param("page") long page);

     @Query(value = "CALL FINDEIGHTCOURSESBYCATEGORY(:page, :p_category);", nativeQuery = true)
     Collection<Course> findNineCoursesByCourses(@Param("page") long page, @Param("p_category") long category);

     @Override
     Optional<Course> findById(Long aLong);
}
