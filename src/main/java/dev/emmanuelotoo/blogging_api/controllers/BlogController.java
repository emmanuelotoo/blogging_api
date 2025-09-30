package dev.emmanuelotoo.blogging_api.controllers;


import dev.emmanuelotoo.blogging_api.dtos.NewBlogDto;
import dev.emmanuelotoo.blogging_api.entity.Blog;
import dev.emmanuelotoo.blogging_api.services.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Creating a new blog post
    @PostMapping("/posts")
    public ResponseEntity<Blog> saveBlog(@RequestBody NewBlogDto blogDto) {
         return blogService.saveBlog(blogDto);
    }

}
