package com.siliconvalley.domain.member.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(Long target){
        super(target + "is not found");
    }
}
