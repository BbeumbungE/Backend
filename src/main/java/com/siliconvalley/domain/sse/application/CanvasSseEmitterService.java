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

    public SseEmitter connect(Long profileId) {

        SseEmitter preSseEmitters = canvasSseEmitterRepository.findById(profileId);
        if (preSseEmitters != null) {
            canvasSseEmitterRepository.delete(profileId);
        }
        SseEmitter sseEmitter = canvasSseEmitterCreater.createEmitter(profileId);

        return sseEmitter;
    }
}