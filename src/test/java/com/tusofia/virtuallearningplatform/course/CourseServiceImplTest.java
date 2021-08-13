package com.tusofia.virtuallearningplatform.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {

    private static final Long COURSE_ID = 1L;
    private static final Long COURSE_ID_NON_EXISTING = 2L;
    private static final String TITLE = "Test Course Title";
    private static final String SHORT_DESCRIPTION = "Test Short Description";
    private static final String FULL_DESCRIPTION = "Test Full Description";
    private static final String REQUIREMENTS = "Test Requirements";
    private static final String LANGUAGE = "Test Language";

    private Course course;
    private List<Course> courseList;
    private CourseService courseService;

    private final ModelMapper courseMapper = new ModelMapper();

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        this.course = new Course();
        this.course.setId(COURSE_ID);
        this.course.setTitle(TITLE);
        this.course.setShortDescription(SHORT_DESCRIPTION);
        this.course.setFullDescription(FULL_DESCRIPTION);
        this.course.setRequirements(REQUIREMENTS);
        this.course.setLanguage(LANGUAGE);

        this.courseList = new ArrayList<>();
        this.courseList.add(this.course);
        this.courseService = new CourseServiceImpl(this.courseRepository);
    }

    @Test
    void totalCourses() {
        when(this.courseRepository.count()).thenReturn(Long.valueOf(this.courseList.size()));
        assertEquals(this.courseList.size(), this.courseService.totalCourses());
    }

    @Test
    void createCourse() {
        when(this.courseRepository.save(any(Course.class))).thenAnswer(inv -> inv.getArgument(0));
        assertNotNull(this.courseService.createCourse(this.courseMapper.map(this.course, CourseDTO.class)));
        CourseDTO courseDTO = this.courseMapper.map(this.course, CourseDTO.class);
        CourseDTO answerDTO = this.courseService.createCourse(this.courseMapper.map(this.course, CourseDTO.class));
        assertEquals(courseDTO.getTitle(), answerDTO.getTitle());
        assertEquals(courseDTO.getShortDescription(), answerDTO.getShortDescription());
        assertEquals(courseDTO.getFullDescription(), answerDTO.getFullDescription());
        assertEquals(courseDTO.getRequirements(), answerDTO.getRequirements());
        assertEquals(courseDTO.getLanguage(), answerDTO.getLanguage());
        assertNotNull(this.courseService.createCourse(CourseDTO.builder().id(COURSE_ID_NON_EXISTING).build()));
    }

    @Test
    void getCourseById() {
        when(this.courseRepository.findById(anyLong()))
                .thenAnswer(inv -> this.courseList.stream().filter(course -> course.getId().equals(inv.getArgument(0))).findFirst());
        assertNotNull(this.courseService.findCourseById(COURSE_ID));
        assertNull(this.courseService.findCourseById(COURSE_ID_NON_EXISTING));
        assertEquals(this.courseMapper.map(course, CourseDTO.class), this.courseService.findCourseById(COURSE_ID));
    }

    @Test
    void findAllCourses() {
        when(this.courseRepository.findAll())
                .thenAnswer(inv -> this.courseList);
        assertNotNull(this.courseService.findAllCourses());
        assertEquals(this.courseList.size(), this.courseService.findAllCourses().size());
    }

    @Test
    void editCourse() {
        when(this.courseRepository.findById(anyLong()))
                .thenAnswer(inv -> this.courseList.stream().filter(course -> course.getId().equals(inv.getArgument(0))).findFirst());
        when(this.courseRepository.save(any(Course.class))).thenAnswer(inv -> inv.getArgument(0));
        assertNull(this.courseService.editCourse(null));
        assertNotNull(this.courseService.editCourse(this.courseMapper.map(course, CourseDTO.class)));
        assertEquals(this.courseMapper.map(course, CourseDTO.class),
                this.courseService.editCourse(this.courseMapper.map(course, CourseDTO.class)));
        assertNull(this.courseService.editCourse(CourseDTO.builder().build()));
        assertNull(this.courseService.editCourse(CourseDTO.builder().id(COURSE_ID_NON_EXISTING).build()));
    }

    @Test
    void deleteCourseById() {
        when(this.courseRepository.findById(anyLong()))
                .thenAnswer(inv -> this.courseList.stream().filter(course -> course.getId().equals(inv.getArgument(0))).findFirst());
        courseService.deleteCourseById(COURSE_ID);
        verify(courseRepository).deleteById(COURSE_ID);
    }

    @Test
    void deleteNonExistingCourseId() {
        when(this.courseRepository.findById(anyLong()))
                .thenAnswer(inv -> this.courseList.stream().filter(course -> course.getId().equals(inv.getArgument(0))).findFirst());
        assertNull(courseService.deleteCourseById(COURSE_ID_NON_EXISTING));
    }
}
