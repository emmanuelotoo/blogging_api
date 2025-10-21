package dev.emmanuelotoo.blogging_api.controller;


import dev.emmanuelotoo.blogging_api.dto.NewBlogDto;
import dev.emmanuelotoo.blogging_api.entity.Blog;
import dev.emmanuelotoo.blogging_api.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
    @RequestMapping("/api/v1")
    public class BlogController {

        private final BlogService blogService;

        public BlogController(BlogService blogService) {
            this.blogService = blogService;
        }

    // Creating a new blog post - working correctly with status codes
    @PostMapping("/post")
    public ResponseEntity<Blog> saveBlog(@RequestBody NewBlogDto blogDto) {
         return blogService.saveBlog(blogDto);
    }

    //Getting all blog posts - working correctly with status codes
     @GetMapping("/posts")
     public ResponseEntity<List<Blog>> getAllBlogs(@RequestParam(required = false) String search) {
            return blogService.getAllBlogs(search);
     }

    //Getting a blog post by its ID - working correctly with status codes
    @GetMapping("/posts/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Integer id) {
            return blogService.getBlogById(id);
    }

    // Deleting a blog post by its ID - working correctly with status codes
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
            return blogService.deleteBlog(id);
    }

    // Updating a blog post by its ID - working correctly with status codes
     @PutMapping("/posts/{id}")
     public ResponseEntity<Blog> updateBlog(@PathVariable Integer id, @RequestBody NewBlogDto blogDto) {
            return blogService.updateBlog(id, blogDto);
     }
    }