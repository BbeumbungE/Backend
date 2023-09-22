package com.siliconvalley.domain.sse.dto;

import com.siliconvalley.domain.sse.code.SseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SseConnectSuccessResponse {

    private long createdAt;
    private String message;
    private Long receiver;

    public SseConnectSuccessResponse(Long profileId) {
        this.createdAt = System.currentTimeMillis();
        this.receiver = profileId;
        this.message = profileId + "번 프로필 " + SseCode.CONNECT_SUCCESS.getMessage();
    }
}
