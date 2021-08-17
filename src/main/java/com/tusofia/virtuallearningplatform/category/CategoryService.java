package com.tusofia.virtuallearningplatform.category;

import com.tusofia.virtuallearningplatform.course.CourseDTO;

import java.util.List;

public interface CategoryService {
    long totalCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO findCategoryById(Long id);
    List<CategoryDTO> findAllTopLevelCategories();
    List<CategoryDTO> findAllMidLevelCategories();
    List<CategoryDTO> findAllLowLevelCategories();
    List<CategoryDTO> findAllCategories();
    List<CourseDTO> findAllCoursesByCategoryId(Long id);
    CategoryDTO editCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategoryById(Long id);
}
