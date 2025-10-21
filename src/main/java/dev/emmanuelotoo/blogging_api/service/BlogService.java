package dev.emmanuelotoo.blogging_api.service;

import dev.emmanuelotoo.blogging_api.dto.NewBlogDto;
import dev.emmanuelotoo.blogging_api.entity.Blog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {
    ResponseEntity<Blog> saveBlog(NewBlogDto blogDto);

    ResponseEntity<List<Blog>> getAllBlogs(String search);

    ResponseEntity<Blog> getBlogById(Integer id);

    ResponseEntity<Void> deleteBlog(Integer id);

    ResponseEntity<Blog> updateBlog(Integer id, NewBlogDto blogDto);

    List<Blog> searchBlogs(String searchTerm);
}