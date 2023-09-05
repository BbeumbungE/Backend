package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.point.Point;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileManagementService {

    private final MemberFindDao memberFindDao;
    private final ProfileRepository profileRepository;
    private final ProfileFindDao profileFindDao;

    public Profile createProfile(final String memberId, final ProfileCreateOrUpdate dto) {
        Profile profile = dto.toEntity(memberFindDao.findById(memberId));
        Point point = Point.builder()
                .point(0L)
                .build();

        profile.setPoint(point);
        return profileRepository.save(profile);
    }

    public ProfileResponse updateProfile(final Long profileId, final ProfileCreateOrUpdate dto) {
        Profile profile = profileFindDao.findById(profileId);
        profile.updateProfile(dto.getProfileName(), dto.getProfileImage());
        return new ProfileResponse(profile);
    }

    public ProfileResponse deleteProfile(Long profileId) {
        Profile profile = profileFindDao.findById(profileId);
        profileRepository.delete(profile);
        SecurityContextHolder.clearContext();
        return new ProfileResponse(profile);
    }
}
