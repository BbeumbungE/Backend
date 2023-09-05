package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCanvas_Subject_SubjectName(String subjectName, Pageable pageable);
}