package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import com.siliconvalley.domain.profile.dto.ProfileCreateSuccessResponse;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.domain.profile.exception.ProfileNameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileManagementService {

    private final MemberFindDao memberFindDao;
    private final ProfileRepository profileRepository;
    private final ProfileFindDao profileFindDao;

    public ProfileCreateSuccessResponse createProfile(final String memberId, final ProfileCreateOrUpdate dto) {
        if (profileRepository.existsByProfileName(dto.getProfileName())) throw new ProfileNameDuplicateException(dto.getProfileName());
        Profile profile = dto.toEntity(memberFindDao.findById(memberId));
        profile.setPoint(profile.buildPoint());
        profileRepository.save(profile);
        return new ProfileCreateSuccessResponse(profile);
    }

    public void updateProfile(final Long profileId, final ProfileCreateOrUpdate dto) {
        Profile profile = profileFindDao.findById(profileId);
        profile.updateProfile(dto.getProfileName(), dto.getProfileImage());
    }

    public void deleteProfile(Long profileId) {
        Profile profile = profileFindDao.findById(profileId);
        profileRepository.delete(profile);
        SecurityContextHolder.clearContext();
    }
}
