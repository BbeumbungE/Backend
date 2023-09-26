package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.dto.SseConnectSuccessResponse;
import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class CanvasSseEmitterService {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    private final CanvasSseEmitterCreater canvasSseEmitterCreater;
    private final SseEmitterSender sseEmitterSender;

    public SseEmitter connect(Long profileId) {

        SseEmitter preSseEmitters = canvasSseEmitterRepository.findById(profileId);
        if (preSseEmitters != null) {
            canvasSseEmitterRepository.delete(profileId);
        }
        SseEmitter sseEmitter = canvasSseEmitterCreater.createEmitter(profileId);

        // 연결 후 즉시 알림을 보내지 않으면 연결이 끊길 수 있으므로 연결 성공 알림 보내기
        String id = profileId + "_" + System.currentTimeMillis();
        sseEmitterSender.send(sseEmitter, id, new SseConnectSuccessResponse(profileId), profileId, "initial");

        return sseEmitter;
    }
}