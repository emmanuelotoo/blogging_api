package dev.emmanuelotoo.blogging_api.repositories;

import dev.emmanuelotoo.blogging_api.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository interface for my Blog entity
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    // used @Query to write a custom query to search for blogs by key terms
    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Blog> findBySearchTerm(@Param("searchTerm")String searchTerm);
}
