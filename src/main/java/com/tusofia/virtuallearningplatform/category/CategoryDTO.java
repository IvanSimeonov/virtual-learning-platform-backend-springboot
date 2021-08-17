package com.tusofia.virtuallearningplatform.category;

import com.tusofia.virtuallearningplatform.course.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String title;
    private Long parentId;
    private List<CategoryDTO> children;
    private List<CourseDTO> courses;
}
