package com.tusofia.virtuallearningplatform.course;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper courseMapper = new ModelMapper();

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public long totalCourses() {
        return this.courseRepository.count();
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setShortDescription(courseDTO.getShortDescription());
        course.setFullDescription(courseDTO.getFullDescription());
        course.setRequirements(courseDTO.getRequirements());
        course.setLanguage(courseDTO.getLanguage());
        return this.courseMapper.map(this.courseRepository.save(course), CourseDTO.class);
    }

    @Override
    public CourseDTO findCourseById(Long id) {
        Course course = this.courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        return this.courseMapper.map(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        return this.courseRepository.findAll().stream()
                .map(course -> this.courseMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = this.courseRepository.findById(courseDTO.getId()).orElse(null);

        if (course == null) {
            return null;
        }

        this.courseRepository.save(this.courseMapper.map(courseDTO, Course.class));
        return this.courseMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO deleteCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        courseRepository.deleteById(id);
        return this.courseMapper.map(course, CourseDTO.class);
    }
}
