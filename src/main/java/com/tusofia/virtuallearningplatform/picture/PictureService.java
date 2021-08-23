package com.tusofia.virtuallearningplatform.picture;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    Picture savePicture(MultipartFile multipartFile) throws IOException;
    Picture findPictureById(Long id);
    List<Picture> findAllPictures();
    Picture deletePictureById(Long id);
}
