package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.dto.SseConnectSuccessResponse;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SseEmitterService {

    private final SseEmitterRepository sseEmitterRepository;
    private final SseEmitterCreater sseEmitterCreater;
    private final SseEmitterSender sseEmitterSender;
    private final SseEmitterFinder sseEmitterFinder;

    private final EventCashService eventCashService;

    public SseEmitter connect(Long profileId, String lastEventId) {

         SseEmitter preSseEmitters = sseEmitterFinder.findByProfileId(profileId);

         if (preSseEmitters != null) {
            sseEmitterRepository.delete(profileId);
         }

        String id = profileId + "_" + System.currentTimeMillis();
        SseEmitter sseEmitter = sseEmitterCreater.createEmitter(profileId);
        sseEmitterSender.send(sseEmitter, id, new SseConnectSuccessResponse(profileId), profileId, "initial");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        eventCashService.sendNotReceivedEvent(profileId, lastEventId, sseEmitter);

        return sseEmitter;
    }
}
