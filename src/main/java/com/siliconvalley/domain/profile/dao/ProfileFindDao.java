package com.siliconvalley.domain.profile.dao;

import com.siliconvalley.domain.member.dao.MemberRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.domain.profile.dto.ProfileResponseList;
import com.siliconvalley.domain.profile.exception.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileFindDao {

    private final ProfileRepository profileRepository;

    public Profile findById(final Long id) {
        final Optional<Profile> profileOptional = profileRepository.findById(id);
        profileOptional.orElseThrow(() -> new ProfileNotFoundException(id));
        return profileOptional.get();
    }

    public ProfileResponse getProfileById(final Long id) {
        final Optional<Profile> profileOptional = profileRepository.findById(id);
        profileOptional.orElseThrow(()-> new ProfileNotFoundException(id));
        return new ProfileResponse(profileOptional.get());
    }

    public ProfileResponseList getProfilesByMemberId(String userId) {
        List<Profile> profileList = profileRepository.findByMemberId(userId);
        return new ProfileResponseList(profileList);
    }
}