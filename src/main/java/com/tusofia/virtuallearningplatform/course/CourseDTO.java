package com.tusofia.virtuallearningplatform.course;

import com.tusofia.virtuallearningplatform.lecture.LectureDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String title;
    private String shortDescription;
    private String fullDescription;
    private String requirements;
    private String language;
    private List<LectureDTO> lectures;
}
