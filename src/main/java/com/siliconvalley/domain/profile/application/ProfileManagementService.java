package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.item.myitem.dao.MyItemRepository;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.code.ProfileItemCode;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dao.ProfileItemFindDao;
import com.siliconvalley.domain.profile.dao.ProfileItemRepository;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import com.siliconvalley.domain.profile.dto.ProfileCreate;
import com.siliconvalley.domain.profile.dto.ProfileCreateSuccessResponse;
import com.siliconvalley.domain.profile.dto.ProfileItemUpdate;
import com.siliconvalley.domain.profile.dto.ProfileNameUpdate;
import com.siliconvalley.domain.profile.exception.ProfileItemNotFoundException;
import com.siliconvalley.domain.profile.exception.ProfileNameDuplicateException;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileManagementService {

    // Member
    private final MemberFindDao memberFindDao;

    // Profile
    private final ProfileRepository profileRepository;
    private final ProfileFindDao profileFindDao;

    // Item
    private final ItemFindDao itemFindDao;

    // MyItem
    private final MyItemRepository myItemRepository;
    private final MyItemFindDao myItemFindDao;

    // ProfileItem
    private final ProfileItemFindDao profileItemFindDao;

    public Response createProfile(final String memberId, final ProfileCreate dto) {
        if (profileRepository.existsByProfileName(dto.getProfileName())) throw new ProfileNameDuplicateException(dto.getProfileName());
        Profile profile = dto.getProfile(memberFindDao.findById(memberId));

        // 기본 아이템 제공
        MyItem myAvatarItem = profile.buildBasicAvatarItem(itemFindDao.findById(1L));
        MyItem mySubjectItem = profile.buildBasicSubjectItem(itemFindDao.findById(2L));

        // 기본 프로필 적용
        ProfileItem profileAvatar = dto.getProfileItem(myAvatarItem);

        // 객체 연관관계 설정
        profile.addMyItem(myAvatarItem);
        profile.addMyItem(mySubjectItem);
        profile.addProfileAvatar(profileAvatar);
        profile.addPoint(profile.buildPoint());
        profileRepository.save(profile);

        return Response.of(ProfileCode.CREATE_SUCCESS, new ProfileCreateSuccessResponse(profile));
    }

    public Response updateProfileName(final Long profileId, final ProfileNameUpdate dto) {
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
