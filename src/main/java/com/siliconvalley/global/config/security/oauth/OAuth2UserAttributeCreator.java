package com.siliconvalley.global.config.security.oauth;

import com.siliconvalley.domain.member.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class OAuth2UserAttributeCreator {
    public static Map<String, Object> createAttribute(Member member) {
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("id", member.getId());
        attribute.put("userId", member.getUserId());
        attribute.put("email", member.getEmail());
        attribute.put("role", member.getRole());
        return attribute;
    }
}
