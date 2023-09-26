package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.code.ProfileItemCode;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dao.ProfileItemFindDao;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import com.siliconvalley.domain.profile.dto.ProfileItemUpdate;
import com.siliconvalley.domain.profile.dto.ProfileNameUpdate;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileManagementService {

    // Profile
    private final ProfileRepository profileRepository;
    private final ProfileFindDao profileFindDao;

    // Item
    private final ItemFindDao itemFindDao;

    // MyItem
    private final MyItemFindDao myItemFindDao;

    // ProfileItem
    private final ProfileItemFindDao profileItemFindDao;

    // BadWordFilter
    private final ProfileNameFilterService nickNameFilterService;

    public Response updateProfileName(final Long profileId, final ProfileNameUpdate dto) {
        nickNameFilterService.profileNameFilter(dto.getProfileName());

        // 닉네임 필터 통과시 프로필 업데이트
        Profile profile = profileFindDao.findById(profileId);
        profile.updateProfileName(dto);
        return Response.of(ProfileCode.PATCH_SUCCESS, null);
    }

    public Response updateProfileAvatar(final Long profileItemId,final ProfileItemUpdate dto) {
        ProfileItem profileItem = profileItemFindDao.findById(profileItemId);
        profileItem.updateMyItem(myItemFindDao.findById(dto.getMyItemId()));
        return Response.of(ProfileItemCode.PATCH_SUCCESS, null);
    }

    public Response deleteProfile(Long profileId) {
        Profile profile = profileFindDao.findById(profileId);
        profileRepository.delete(profile);
        SecurityContextHolder.clearContext();
        return Response.of(ProfileCode.DELETE_SUCCESS, null);
    }
}
