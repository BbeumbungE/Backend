package com.siliconvalley.domain.profile.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileNameUpdate {

    @Valid
    private String profileName;

    public ProfileNameUpdate(@Valid String profileName){
        this.profileName = profileName;
    }
}
