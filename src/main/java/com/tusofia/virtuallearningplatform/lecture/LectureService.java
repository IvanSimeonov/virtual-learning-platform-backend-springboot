package com.tusofia.virtuallearningplatform.lecture;

import java.util.List;

public interface LectureService {
    LectureDTO createLecture(LectureDTO lectureDTO);
    LectureDTO findLectureById(Long id);
    List<LectureDTO> findAllLecturesByCourseId(Long id);
    List<LectureDTO> findAllLectures();
    LectureDTO editLecture(LectureDTO lectureDTO);
    LectureDTO deleteLectureById(Long id);
}
