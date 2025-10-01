package dev.emmanuelotoo.blogging_api.controllers;


import dev.emmanuelotoo.blogging_api.dtos.NewBlogDto;
import dev.emmanuelotoo.blogging_api.entity.Blog;
import dev.emmanuelotoo.blogging_api.services.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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
         List<Blog> blogs;
         if (search != null && !search.trim().isEmpty()) {
             blogs = blogService.searchBlogs(search);
         } else {
             blogs = blogService.getAllBlogs();
         }
         return ResponseEntity.ok(blogs);
     }

    //Getting a blog post by its ID - working correctly with status codes
    @GetMapping("/posts/{id}")
    public ResponseEntity<Optional<Blog>> getBlogById(@PathVariable Integer id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deleting a blog post by its ID - working correctly with status codes
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
        if (blogService.getBlogById(id).isPresent()) {
            blogService.deleteBlog(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Updating a blog post by its ID - working correctly with status codes
     @PutMapping("/posts/{id}")
     public ResponseEntity<Blog> updateBlog(@PathVariable Integer id, @RequestBody NewBlogDto blogDto) {
         return blogService.getBlogById(id)
             .map(existingBlog -> ResponseEntity.ok(blogService.updateBlog(id, blogDto)))
             .orElse(ResponseEntity.notFound().build());
     }
    }
