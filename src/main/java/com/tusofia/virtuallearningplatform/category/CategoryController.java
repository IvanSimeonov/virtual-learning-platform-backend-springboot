package com.tusofia.virtuallearningplatform.category;

import com.tusofia.virtuallearningplatform.course.CourseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Category Controller", tags = {"categories"})
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Retrieves the total amount of categories")
    @GetMapping("/totalcategories")
    public long totalCategories() {
        return this.categoryService.totalCategories();
    }

    @ApiOperation(value = "Creates a new category")
    @PostMapping("/category/create")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return this.categoryService.createCategory(categoryDTO);
    }

    @ApiOperation(value = "Retrieves a category by id")
    @GetMapping("/category/{id}")
    public CategoryDTO findCategoryById(@PathVariable Long id) {
        return this.categoryService.findCategoryById(id);
    }

    @ApiOperation(value = "Retrieves all top level categories")
    @GetMapping("/categories/top")
    public List<CategoryDTO> findAllTopLevelCategories() {
        return this.categoryService.findAllTopLevelCategories();
    }

    @ApiOperation(value = "Retrieves all mid level categories")
    @GetMapping("/categories/mid")
    public List<CategoryDTO> findAllMidLevelCategories() {
        return this.categoryService.findAllMidLevelCategories();
    }

    @ApiOperation(value = "Retrieves all lowest level categories")
    @GetMapping("/categories/low")
    public List<CategoryDTO> findAllLowLevelCategories() {
        return this.categoryService.findAllLowLevelCategories();
    }

    @ApiOperation(value = "Retrieves all categories")
    @GetMapping("/categories")
    public List<CategoryDTO> findAllCategories() {
        return this.categoryService.findAllCategories();
    }

    @ApiOperation(value = "Retrieves all courses by the category id they have assigned")
    @GetMapping("/category/{id}/courses")
    public List<CourseDTO> findAllCoursesByCategoryId(@PathVariable Long id) {
        return this.categoryService.findAllCoursesByCategoryId(id);
    }

    @ApiOperation(value = "Edits an existing category")
    @PutMapping("/category/edit")
    public CategoryDTO editCategory(@RequestBody CategoryDTO categoryDTO) {
        return this.categoryService.editCategory(categoryDTO);
    }

    @ApiOperation(value = "Deletes an existing category by id")
    @DeleteMapping("/category/delete/{id}")
    public CategoryDTO deleteCategoryById(@PathVariable Long id) {
        return this.categoryService.deleteCategoryById(id);
    }
}
