package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.PostCreatedResponse;
import com.siliconvalley.domain.post.service.PostingService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfilePostingService {

    private final ProfileFindDao profileFindDao;
    private final CanvasFindDao canvasFindDao;
    private final ProfileRepository profileRepository;
    private final PostingService postingService;

    public Response createPostForProfile(Long profileId, Long canvasId){
        Profile profile = profileFindDao.findById(profileId);
        Canvas canvas = canvasFindDao.findById(canvasId);
        Post post = postingService.createPost(profile, canvas);
        profileRepository.save(profile);
        return Response.of(PostCode.POSTING_SUCCESS, new PostCreatedResponse(post);
    }

    public Response deletePostForProfile(Long profileId, Long postId){
        Profile profile = profileFindDao.findById(profileId);
        Response response = postingService.deletePost(postId, profile);
        return response;
    }

}
