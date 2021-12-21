package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select * from (select ID, NAME, CATEGORY, DESCRIPTION, IMAGE, PRICE, HIDE, AUTHOR, COMPLEXITY, row_number() over(order by course.ID) rn from course ) as Nr  where rn  between  Nr.page and Nr.page + 10")
    Collection<Course> findTenCourses(int page);
}
