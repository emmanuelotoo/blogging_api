package dev.emmanuelotoo.blogging_api.service;

import dev.emmanuelotoo.blogging_api.entity.Blog;
import dev.emmanuelotoo.blogging_api.dto.NewBlogDto;
import dev.emmanuelotoo.blogging_api.repository.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //Creating a new blog post
    @Override
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
    @Override
    public ResponseEntity<List<Blog>> getAllBlogs(String search) {
        List<Blog> blogs = (search != null && !search.trim().isEmpty())
                ? blogRepository.findBySearchTerm(search.trim())
                : blogRepository.findAll();

        return ResponseEntity.ok(blogs);
    }

    //Getting a blog post by its ID
    @Override
    public ResponseEntity<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Deleting a blog post by its ID
    @Override
    public ResponseEntity<Void> deleteBlog(Integer id) {
        return blogRepository.findById(id)
                .map(existingBlog -> {
                    blogRepository.delete(existingBlog);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Updating a blog post
    @Override
    public ResponseEntity<Blog> updateBlog(Integer id, NewBlogDto blogDto) {
        return blogRepository.findById(id)
                .map(blog -> {
                    // Check if the title is empty or null, if so, retain the existing content
                    String title = blogDto.title().trim().isEmpty() ? blog.getTitle() : blogDto.title();
                    blog.setTitle(title);

                    // Check if content is empty or null, if so, retain the existing content
                    String content = blogDto.content().trim().isEmpty() ? blog.getContent() : blogDto.content();
                    blog.setContent(content);

                    // Check if the category is empty or null, if so, retain the existing category
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

                    Blog updatedBlog = blogRepository.save(blog);
                    return ResponseEntity.ok(updatedBlog);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    //Searching for blogs by a search term
    @Override
    public List<Blog> searchBlogs(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return blogRepository.findAll();
        }
        return blogRepository.findBySearchTerm(searchTerm.trim());
    }
}