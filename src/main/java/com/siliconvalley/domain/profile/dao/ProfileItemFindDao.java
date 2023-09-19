package com.siliconvalley.domain.profile.dao;

import com.siliconvalley.domain.profile.domain.ProfileItem;
import com.siliconvalley.domain.profile.exception.ProfileItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileItemFindDao {

    private final ProfileItemRepository profileItemRepository;

    public ProfileItem findById(Long profileItemId) {
        Optional<ProfileItem> profileItem = profileItemRepository.findById(profileItemId);
        profileItem.orElseThrow(() -> new ProfileItemNotFoundException(profileItemId));
        return profileItem.get();
    }
}
