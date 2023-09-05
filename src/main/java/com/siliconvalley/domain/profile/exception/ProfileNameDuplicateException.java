package com.siliconvalley.domain.profile.exception;

import com.siliconvalley.global.error.exception.BusinessException;
import com.siliconvalley.global.error.exception.ErrorCode;
import com.siliconvalley.global.error.exception.InvalidValueException;

public class ProfileNameDuplicateException extends InvalidValueException {
    public ProfileNameDuplicateException(String profileName) {
        super(profileName, ErrorCode.PROFILE_NAME_DUPLICATE);
    }
}
