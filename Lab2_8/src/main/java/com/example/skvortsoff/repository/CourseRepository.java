package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
     @Query(value = "select * from Course c where upper(c.name) like upper(concat('%', ?1, '%'))", nativeQuery = true)
     Collection<Course> findByNameContainingIgnoreCase( String name);

     @Query(value = "CALL FINDEIGHTCOURSES(:page);", nativeQuery = true)
     Collection<Course> findNineCourses(@Param("page") long page);
}
