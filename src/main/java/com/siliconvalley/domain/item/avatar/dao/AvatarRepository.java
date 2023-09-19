package com.siliconvalley.domain.item.avatar.dao;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
