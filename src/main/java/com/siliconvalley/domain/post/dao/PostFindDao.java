package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostFindDao {

    private final PostRepository postRepository;

    public Post findById(Long targetId){
        final Optional<Post> post = postRepository.findById(targetId);
        post.orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다."));
        return post.get();
    }

    public Page<Post> findAll(Pageable pageable){
        final Page<Post> posts = postRepository.findAll(pageable);
        return posts;
    }
}
