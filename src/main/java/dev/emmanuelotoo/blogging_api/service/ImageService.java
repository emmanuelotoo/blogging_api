package dev.emmanuelotoo.blogging_api.service;

import dev.emmanuelotoo.blogging_api.entity.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {
    ResponseEntity<Image> uploadImage(MultipartFile file) throws IOException;

    ResponseEntity<Optional<Image>> getImageById(Long id);
}
