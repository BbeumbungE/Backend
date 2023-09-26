package com.siliconvalley.domain.profile.application;


import com.siliconvalley.domain.post.service.PostingService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
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
    private final PostingService postingService;

    public Response deletePostForProfile(Long profileId, Long postId){
        Profile profile = profileFindDao.findById(profileId);
        Response response = postingService.deletePost(postId, profile);
        return response;
    }

}
