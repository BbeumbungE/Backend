package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.dao.CanvasRepository;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.dao.PostRepository;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.PostCreatedResponse;
import com.siliconvalley.domain.post.exception.IllegalDeleteException;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostingService {

    private final PostFindDao postFindDao;
    private final PostRepository postRepository;
    private final CanvasRepository canvasRepository;
    private final CanvasFindDao canvasFindDao;

    public Response createPostForCanvas(Long canvasId){
        Canvas canvas = canvasFindDao.findById(canvasId);
        Post post = canvas.buildPost();
        postRepository.save(post);
        log.info(post.getId() + "번 아이디");
        return Response.of(PostCode.POSTING_SUCCESS, new PostCreatedResponse(post));
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
