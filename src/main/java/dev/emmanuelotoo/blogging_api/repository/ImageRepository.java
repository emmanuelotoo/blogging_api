package dev.emmanuelotoo.blogging_api.repository;

import dev.emmanuelotoo.blogging_api.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
