package dev.emmanuelotoo.blogging_api.service;

import dev.emmanuelotoo.blogging_api.entity.Image;
import dev.emmanuelotoo.blogging_api.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // Uploading an image to the database
    @Override
    public ResponseEntity<Image> uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setData(file.getBytes());

        Image uploadedImage = imageRepository.save(image);

        return ResponseEntity.status(HttpStatus.OK).body(uploadedImage);
    }


    // Getting an image by id
    @Override
    public ResponseEntity<byte[]> getImageById(Long id) {
        return imageRepository.findById(id)
                .map(img -> ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.valueOf(img.getType()))
                        .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + img.getName() + "\"")
                        .body(img.getData()))
                .orElse(ResponseEntity.notFound().build());
    }
    }
