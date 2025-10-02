package dev.emmanuelotoo.blogging_api.services;

import dev.emmanuelotoo.blogging_api.entity.Blog;
import dev.emmanuelotoo.blogging_api.dtos.NewBlogDto;
import dev.emmanuelotoo.blogging_api.repositories.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //Creating a new blog post
    public ResponseEntity<Blog> saveBlog(NewBlogDto blogDto) {

        Blog blog = new Blog();
        blog.setTitle(blogDto.title());
        blog.setContent(blogDto.content());
        blog.setCategory(blogDto.category());
        blog.setTags(blogDto.tags());

        Blog savedBlog = blogRepository.save(blog);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

    //Getting all the blog posts
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    //Getting a blog post by its ID
    public Optional<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id);
    }

    //Deleting a blog post by its ID
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }

    //Updating a blog post
    public Blog updateBlog(Integer id, NewBlogDto blogDto) {

        Blog blog = blogRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Blog with id" + id + " not found"));


        // Check if title is empty or null, if so, retain the existing content
        String title = blogDto.title().trim().isEmpty() ? blog.getTitle() : blogDto.title();
        blog.setTitle(title);


        // Check if content is empty or null, if so, retain the existing content
        String content = blogDto.content().trim().isEmpty() ? blog.getContent() : blogDto.content();
        blog.setContent(content);


        // Check if category is empty or null, if so, retain the existing category
        String category = blogDto.category().trim().isEmpty() ? blog.getCategory() : blogDto.category();
        blog.setCategory(category);




        // Adding new tags to the existing tags instead of replacing them

        List<String> tags = new ArrayList<>(blog.getTags());

        for(String tag : blogDto.tags()) {
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }

        blog.setTags(tags);

        return blogRepository.save(blog);
    }


    //Searching for blogs by a search term
    public List<Blog> searchBlogs(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return blogRepository.findAll();
        }
        return blogRepository.findBySearchTerm(searchTerm.trim());
    }
}
