package com.siliconvalley.domain.profile.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileItemUpdate {

    @Valid
    private Long myItemId;

    public ProfileItemUpdate(@Valid Long myItemId) {
        this.myItemId = myItemId;
    }
}
