package com.siliconvalley.domain.profile.api;

import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.profile.application.ProfileManagementService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileApi {

    private final ProfileFindDao profileFindDao;
    private final ProfileManagementService profileManagementService;
    private final MyItemFindDao myItemFindDao;
    private final MyItemCreateService myItemCreateService;

    @PostMapping
    public Response createProfile(
            @RequestBody @Valid final ProfileCreateOrUpdate dto,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        String memberId = (String) oAuth2User.getAttributes().get("id");
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profileManagementService.createProfile(memberId, dto)));
    }

    @GetMapping("/{profileId}")
    public Response getProfile(
            @PathVariable("profileId") Long profileId
    ) {
        return Response.of(CommonCode.GOOD_REQUEST, profileFindDao.getProfileById(profileId));
    }

    @PatchMapping("/{profileId}")
    public Response updateProfile(
            @PathVariable("profileId") Long profileId,
        @RequestBody @Valid final ProfileCreateOrUpdate dto
    ) {
        return Response.of(CommonCode.GOOD_REQUEST, profileManagementService.updateProfile(profileId, dto));
    }

    @DeleteMapping("/{profileId}")
    public Response deleteProfile(
        @PathVariable("profileId") Long profileId
    ) {
        return Response.of(CommonCode.GOOD_REQUEST, profileManagementService.deleteProfile(profileId));
    }

    @GetMapping("/{profileId}/items")
    public Response getItemsOfProfile(
            @PathVariable("profileId") Long profileId,
            @RequestParam(value = "category", required = false) String category,
            Pageable pageable
    ) {
        return Response.of(CommonCode.GOOD_REQUEST, myItemFindDao.getMyItemListByPage(profileId, pageable, category));
    }

    @PostMapping("/{profileId}/items/{itemId}")
    public Response purchaseSubjectItem(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "itemId") Long itemId,
            @RequestParam(value = "category") String category // subject or avatar
    ) {
        return Response.of(CommonCode.SUCCESS_CREATE, myItemCreateService.createMyItem(profileId, itemId, category));
    }
}
