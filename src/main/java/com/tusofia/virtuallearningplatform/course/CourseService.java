package com.tusofia.virtuallearningplatform.course;

import java.util.List;

public interface CourseService {
    long totalCourses();
    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO findCourseById(Long id);
    List<CourseDTO> findAllCourses();
    CourseDTO editCourse(CourseDTO courseDTO);
    CourseDTO deleteCourseById(Long id);
}
