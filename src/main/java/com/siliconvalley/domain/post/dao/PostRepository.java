package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}