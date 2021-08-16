package com.tusofia.virtuallearningplatform.lecture;

import com.tusofia.virtuallearningplatform.course.Course;
import com.tusofia.virtuallearningplatform.course.CourseDTO;
import com.tusofia.virtuallearningplatform.course.CourseRepository;
import com.tusofia.virtuallearningplatform.course.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final ModelMapper lectureMapper = new ModelMapper();
    private final ModelMapper courseMapper = new ModelMapper();

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, CourseRepository courseRepository, CourseService courseService) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @Override
    public LectureDTO createLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        lecture.setTitle(lectureDTO.getTitle());
        lecture.setDescription(lectureDTO.getDescription());
        lecture.setCourse(this.courseMapper.map(this.courseRepository.getById(lectureDTO.getCourse_id()), Course.class));
        LectureDTO lectureDTO1 = this.lectureMapper.map(this.lectureRepository.save(lecture),LectureDTO.class);
        lectureDTO1.setCourse_id(lecture.getCourse().getId());
        return lectureDTO1;
    }

    @Override
    public LectureDTO findLectureById(Long id) {
        Lecture lecture = this.lectureRepository.findById(id).orElse(null);
        if (lecture == null) {
            return null;
        }
        return this.lectureMapper.map(lecture, LectureDTO.class);
    }

    @Override
    public List<LectureDTO> findAllLecturesByCourseId(Long id) {
        CourseDTO course = this.courseService.findCourseById(id);
        if (course == null) {
            return null;
        }
        return course.getLectures().stream().map(lecture -> lectureMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<LectureDTO> findAllLectures() {
        return this.lectureRepository.findAll().stream()
                .map(lecture -> this.lectureMapper.map(lecture, LectureDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LectureDTO editLecture(LectureDTO lectureDTO) {
        if (lectureDTO == null) {
            return null;
        }
        Lecture lecture = this.lectureRepository.findById(lectureDTO.getId()).orElse(null);
        if (lecture == null) {
            return null;
        }
        this.lectureRepository.save(this.lectureMapper.map(lectureDTO, Lecture.class));
        return this.lectureMapper.map(lecture, LectureDTO.class);
    }

    @Override
    public LectureDTO deleteLectureById(Long id) {
        Lecture lecture = this.lectureRepository.findById(id).orElse(null);
        if (lecture == null) {
            return null;
        }
        this.lectureRepository.deleteById(id);
        return this.lectureMapper.map(lecture, LectureDTO.class);
    }
}
