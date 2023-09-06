package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.dao.PostRepository;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.exception.IllegalDeleteException;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingService {

    private final PostFindDao postFindDao;
    private final PostRepository postRepository;


    public Post createPost(Profile profile, Canvas canvas){
        Post post = profile.addPost(canvas);
        return post;
    }

    public Response deletePost(Long postId, Profile profile){
        Post post = postFindDao.findById(postId);
        if (profile.getId() != post.getId()){
            throw new IllegalDeleteException("잘못된 요청");
        }
        postRepository.delete(post);
        return Response.of(PostCode.DELETE_POST_SUCCESS, null);
    }
}
