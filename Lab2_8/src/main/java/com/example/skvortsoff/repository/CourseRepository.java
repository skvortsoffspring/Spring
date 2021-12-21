package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
     @Query(value = "CALL FINDNINECOURSES(:page);", nativeQuery = true)
     Collection<Course> findNineCourses(@Param("page") int page);
}
