package com.siliconvalley.domain.profile.exception;

import com.siliconvalley.global.error.exception.ErrorCode;
import com.siliconvalley.global.error.exception.InvalidValueException;

public class ProfileNameIncludeBadWordException extends InvalidValueException {
    public ProfileNameIncludeBadWordException(String profileName) {
        super(profileName, ErrorCode.PROFILE_NAME_INCLUDE_BAD_WORD);
    }
}
