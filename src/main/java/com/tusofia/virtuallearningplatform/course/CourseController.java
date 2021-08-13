package com.tusofia.virtuallearningplatform.course;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Course Controller", tags = {"course"})
@RestController
@RequestMapping("/api/v1")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "Retrieves the total amount of courses")
    @GetMapping("/totalcourses")
    public long findTotalCoursesAmount() {
        return this.courseService.totalCourses();
    }

    @ApiOperation(value = "Retrieves a course by id")
    @GetMapping("/course/{id}")
    public CourseDTO findCourseById(@PathVariable Long id) {
        return this.courseService.findCourseById(id);
    }

    @ApiOperation(value = "Creates a new course")
    @PostMapping("/course/create")
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return this.courseService.createCourse(courseDTO);
    }

    @ApiOperation(value = "Retrieves all courses")
    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return this.courseService.findAllCourses();
    }

    @ApiOperation(value = "Edits an existing course")
    @PutMapping("/course/edit")
    public CourseDTO editCourseById(@RequestBody CourseDTO courseDTO) {
        return this.courseService.editCourse(courseDTO);
    }

    @ApiOperation(value = "Deletes an existing course")
    @DeleteMapping("/course/delete/{id}")
    public CourseDTO deleteCourseById(@PathVariable Long id) {
        return this.courseService.deleteCourseById(id);
    }
}
