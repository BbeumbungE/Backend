package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.point.domain.Point;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.BasicProfileItem;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import com.siliconvalley.domain.profile.dto.ProfileCreate;
import com.siliconvalley.domain.profile.dto.ProfileCreateSuccessResponse;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileCreateService {

    private final MemberFindDao memberFindDao;
    private final ProfileRepository profileRepository;
    private final MyItemCreateService myItemCreateService;
    private final ProfileNameFilterService profileNameFilterService;

    public Response createProfile(final String memberId, final ProfileCreate dto) {
        profileNameFilterService.profileNameFilter(dto.getProfileName());
        // 프로필 네임 필터 통과시 로직
        Profile profile = dto.toEntity(memberFindDao.findById(memberId));

        // 기본 myItem(Avatar) 생성
        List<MyItem> basicAvatarItemList = myItemCreateService.createBasicItem(profile, "avatar");
        for (MyItem basicAvatar : basicAvatarItemList) {
            profile.addMyItem(basicAvatar);
            if (basicAvatar.getItem().getAvatar().getAvatarName()
                    .equals(BasicProfileItem.BASIC_HAMSTER.getItemName())) {
                profile.addProfileAvatar(ProfileItem.toEntity(profile, basicAvatar));
            }
        }

        // 기본 myItem(Subject) 생성
        List<MyItem> basicSubjectItemList = myItemCreateService.createBasicItem(profile, "subject");
        basicSubjectItemList.stream().forEach(basicSubject -> profile.addMyItem(basicSubject));

        // 포인트 생성
        profile.addPoint(Point.toEntity(profile));

        profileRepository.save(profile);

        return Response.of(ProfileCode.CREATE_SUCCESS, new ProfileCreateSuccessResponse(profile));
    }
}
