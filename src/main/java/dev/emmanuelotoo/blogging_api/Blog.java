package dev.emmanuelotoo.blogging_api;

import java.util.ArrayList;
import java.util.Objects;

public class Blog {
    private String title;
    private String content;
    private String category;
    private ArrayList<String> tags;

    public Blog() { // my default constructor
    }

    public Blog(String title, String content, String category, ArrayList<String> tags) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(title, blog.title) && Objects.equals(content, blog.content) && Objects.equals(category, blog.category) && Objects.equals(tags, blog.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, category, tags);
    }
} // Following the POJO pattern to represent data structures
