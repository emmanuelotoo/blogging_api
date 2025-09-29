package dev.emmanuelotoo.blogging_api.services;

import dev.emmanuelotoo.blogging_api.domain.Blog;
import dev.emmanuelotoo.blogging_api.dtos.NewBlogDto;
import dev.emmanuelotoo.blogging_api.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //Creating a new blog post
    public Blog saveBlog(NewBlogDto blogDto) {

        Blog blog = new Blog();
        blog.setTitle(blogDto.title());
        blog.setContent(blogDto.content());
        blog.setCategory(blogDto.category());
        blog.setTags(blogDto.tags());

        return blogRepository.save(blog);
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


}
