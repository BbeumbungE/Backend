package com.siliconvalley.domain.profile.dao;

import com.siliconvalley.domain.profile.domain.ProfileItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileItemRepository extends JpaRepository<ProfileItem, Long> {
}
