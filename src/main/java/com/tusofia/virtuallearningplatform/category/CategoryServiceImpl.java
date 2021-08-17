package com.tusofia.virtuallearningplatform.category;

import com.tusofia.virtuallearningplatform.course.Course;
import com.tusofia.virtuallearningplatform.course.CourseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper categoryMapper = new ModelMapper();
    private final ModelMapper courseMapper = new ModelMapper();

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public long totalCategories() {
        return this.categoryRepository.count();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        if (categoryDTO.getChildren() != null) {
            category.setCourses(categoryDTO.getCourses().stream().map(course -> this.courseMapper.map(course, Course.class)).collect(Collectors.toList()));
        }
        if (categoryDTO.getParentId() != null) {
            category.setParent(this.categoryMapper.map((findCategoryById(categoryDTO.getParentId())), Category.class));
        }
        if (categoryDTO.getChildren() != null) {
            category.setChildren(categoryDTO.getChildren().stream().map(childCategory -> this.categoryMapper.map(childCategory, Category.class)).collect(Collectors.toList()));
        }
        return this.categoryMapper.map(this.categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return this.categoryMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAllTopLevelCategories() {
        List<Category> categories = this.categoryRepository.findAll().stream().filter(category -> category.getParent() == null).collect(Collectors.toList());
        return categories.stream().map(category -> this.categoryMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAllMidLevelCategories() {
        List<Category> categories = this.categoryRepository.findAll().stream().filter(category -> category.getParent() != null && category.getChildren().size() != 0).collect(Collectors.toList());
        return categories.stream().map(category -> this.categoryMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAllLowLevelCategories() {
        List<Category> categories = this.categoryRepository.findAll().stream().filter(category -> category.getParent() != null && category.getChildren().size() == 0).collect(Collectors.toList());
        return categories.stream().map(category -> this.categoryMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        return this.categoryRepository.findAll().stream().map(category -> this.categoryMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findAllCoursesByCategoryId(Long id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return category.getCourses().stream().map(course -> this.courseMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO editCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = this.categoryRepository.findById(categoryDTO.getId()).orElse(null);
        if (category == null) {
            return null;
        }
        this.categoryRepository.save(this.categoryMapper.map(categoryDTO, Category.class));
        return this.categoryMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        this.categoryRepository.deleteById(id);
        return this.categoryMapper.map(category, CategoryDTO.class);
    }
}
