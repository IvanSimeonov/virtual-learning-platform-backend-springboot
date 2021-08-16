package com.tusofia.virtuallearningplatform.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureDTO {
    private Long id;
    private String title;
    private String description;
    private Long course_id;
}
