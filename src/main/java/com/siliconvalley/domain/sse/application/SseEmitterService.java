package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class SseEmitterService {

    private final SseEmitterCreater sseEmitterCreater;
    private final SseEmitterSender sseEmitterSender;

    private final EventCashService eventCashService;

    public SseEmitter connect(Long profileId, String lastEventId) {
        String id = profileId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = sseEmitterCreater.createEmitter(id);
        sseEmitterSender.send(sseEmitter, id, SseCode.CONNECT_SUCCESS.getMessage() + " | Profile Id : " + profileId);

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        eventCashService.sendNotReceivedEvent(String.valueOf(profileId), lastEventId, sseEmitter);

//        return Response.of(SseCode.CONNECT_SUCCESS, sseEmitter);
        return sseEmitter;
    }


}
