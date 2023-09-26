package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.dto.SubjectCachingDto;
import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.PostListResponse;
import com.siliconvalley.domain.post.dto.PostSubjectResponse;
import com.siliconvalley.domain.post.exception.PostNotFoundException;
import com.siliconvalley.global.common.dto.Response;
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
    private final PostCustomRepository postCustomRepository;
    private final SubjectFindDao subjectFindDao;

    public Post findById(Long targetId){
        final Optional<Post> post = postRepository.findById(targetId);
        post.orElseThrow(() -> new PostNotFoundException("post number : " + targetId ));
        return post.get();
    }

    public Response findAll(Pageable pageable){
        final Page<Post> posts = postRepository.findAll(pageable);
        return Response.of(PostCode.POST_RETRIEVE_SUCCESS, posts);
    }

    public Response getPostsBySubjectName(Long subjectId, Pageable pageable) {
        Page<PostListResponse> postListResponses = postCustomRepository.findBySubjectId(subjectId, pageable);
        SubjectCachingDto dto = subjectFindDao.findSubjectForCachingById(subjectId);
        return Response.of(PostCode.POST_RETRIEVE_SUCCESS, new PostSubjectResponse(dto, postListResponses));
    }

}
