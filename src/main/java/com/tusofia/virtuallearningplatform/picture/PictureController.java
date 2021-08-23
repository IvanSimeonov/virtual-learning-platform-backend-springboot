package com.tusofia.virtuallearningplatform.picture;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(description = "Picture Controller", tags = {"picture"})
@RestController
@RequestMapping("/api/v1")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @ApiOperation(value = "Gets all pictures")
    @GetMapping("/pictures")
    public List<Picture> findAllPictures() {
        return this.pictureService.findAllPictures();
    }

    @ApiOperation(value = "Downloads a picture by id")
    @GetMapping("/pictures/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadPictureById(@PathVariable Long id) {
        Picture picture = pictureService.findPictureById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(picture.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + picture.getName() + "\"")
                .body(new ByteArrayResource(picture.getData()));
    }

    @ApiOperation(value = "Uploads a new picture")
    @PostMapping(value = "/pictures/upload", consumes = "multipart/form-data")
    public String uploadPicture(@RequestParam MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            pictureService.savePicture(file);
        }
        return "Successfully uploaded";
    }

    @ApiOperation(value = "Deletes a picture by id")
    @DeleteMapping("/pictures/delete/{id}")
    public Picture deletePictureById(@PathVariable Long id) {
        return this.pictureService.deletePictureById(id);
    }


}
