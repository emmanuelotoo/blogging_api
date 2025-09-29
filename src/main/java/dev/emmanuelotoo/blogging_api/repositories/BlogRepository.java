package dev.emmanuelotoo.blogging_api.repositories;

import dev.emmanuelotoo.blogging_api.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository interface for my Blog entity
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
