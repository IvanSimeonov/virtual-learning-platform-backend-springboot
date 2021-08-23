package com.tusofia.virtuallearningplatform.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture savePicture(MultipartFile multipartFile) throws IOException {
        Picture picture = new Picture();
        picture.setName(multipartFile.getOriginalFilename());
        picture.setType(multipartFile.getContentType());
        picture.setData(multipartFile.getBytes());
        return this.pictureRepository.save(picture);
    }

    @Override
    public Picture findPictureById(Long id) {
        return this.pictureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Picture> findAllPictures() {
        return this.pictureRepository.findAll();
    }

    @Override
    public Picture deletePictureById(Long id) {
        Picture picture =  this.pictureRepository.findById(id).orElse(null);
        this.pictureRepository.deleteById(id);
        return picture;
    }
}
