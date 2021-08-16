package com.tusofia.virtuallearningplatform.lecture;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Lecture Controller", tags = {"lecture"})
@RestController
@RequestMapping("/api/v1")
public class LectureController {

    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @ApiOperation(value = "Creates a new lecture")
    @PostMapping("/lecture/create")
    public LectureDTO createLecture(@RequestBody LectureDTO lectureDTO) {
        return this.lectureService.createLecture(lectureDTO);
    }

    @ApiOperation(value = "Retrieves a lecture by id")
    @GetMapping("/lecture/{id}")
    public LectureDTO findLectureById(@PathVariable Long id) {
        return this.lectureService.findLectureById(id);
    }

    @ApiOperation(value = "Retrieves all existing lectures")
    @GetMapping("/lectures")
    public List<LectureDTO> findAllLectures() {
        return this.lectureService.findAllLectures();
    }

    @ApiOperation(value = "Retrieve all lectures in the course by the course id")
    @GetMapping("/course/{id}/lectures")
    public List<LectureDTO> findAllLecturesByCourseId(@PathVariable Long id) {
        return this.lectureService.findAllLecturesByCourseId(id);
    }

    @ApiOperation(value = "Edits an existing lecture")
    @PutMapping("/lecture/edit")
    public LectureDTO editLecture(@RequestBody LectureDTO lectureDTO) {
        return this.lectureService.editLecture(lectureDTO);
    }

    @ApiOperation(value = "Deletes an existing lecture by id")
    @DeleteMapping("/lecture/delete/{id}")
    public LectureDTO deleteLectureById(@PathVariable Long id) {
        return this.lectureService.deleteLectureById(id);
    }

}
