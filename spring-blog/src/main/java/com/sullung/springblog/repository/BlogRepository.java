package com.sullung.springblog.repository;

import com.sullung.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
    void deletePostById(Long id);
}
