package com.siliconvalley.domain.profile.api;

import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.profile.application.ProfileManagementService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileApi {

    private final MemberFindDao memberFindDao;
    private final ProfileFindDao profileFindDao;
    private final ProfileManagementService profileManagementService;

    @PostMapping
    public Response createProfile(
            @RequestBody @Valid final ProfileCreateOrUpdate dto,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        String id = (String) oAuth2User.getAttributes().get("id");
        Member member = memberFindDao.findById(id);
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profileManagementService.createProfile(member, dto)));
    }

    @GetMapping("/{profileId}")
    public Response getProfile(
            @PathVariable("profileId") Long profileId
    ) {
        Profile profile = profileFindDao.findById(profileId);
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profile));
    }

    @PatchMapping("/{profileId}")
    public Response updateProfile(
            @PathVariable("profileId") Long profileId,
        @RequestBody @Valid final ProfileCreateOrUpdate dto
    ) {
        Profile profile = profileFindDao.findById(profileId);
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profileManagementService.updateProfile(profile, dto)));
    }

    @DeleteMapping("/{profileId}")
    public Response deleteProfile(
        @PathVariable("profileId") Long profileId
    ) {
        Profile profile = profileFindDao.findById(profileId);
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profileManagementService.deleteProfile(profile)));
    }
}
