package com.siliconvalley.domain.item.avatar.dto;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AvatarResponse {

    private Long id;
    private String avatarName;
    private String avatarImage;

    public AvatarResponse(Avatar avatar) {
        this.id = avatar.getId();
        this.avatarName = avatar.getAvatarName();
        this.avatarImage = avatar.getAvatarImage();
    }
}
