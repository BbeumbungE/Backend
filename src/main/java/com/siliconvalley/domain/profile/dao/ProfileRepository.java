package com.siliconvalley.domain.profile.dao;

import com.siliconvalley.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByMemberId(String memberId);
    boolean existsByProfileName(String profileName);
}
