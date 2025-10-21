package dev.emmanuelotoo.blogging_api.service;

import dev.emmanuelotoo.blogging_api.dto.NewBlogDto;
import dev.emmanuelotoo.blogging_api.entity.Blog;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    ResponseEntity<Blog> saveBlog(NewBlogDto blogDto);

    List<Blog> getAllBlogs();

    Optional<Blog> getBlogById(Integer id);

    void deleteBlog(Integer id);

    Blog updateBlog(Integer id, NewBlogDto blogDto);

    List<Blog> searchBlogs(String searchTerm);
}