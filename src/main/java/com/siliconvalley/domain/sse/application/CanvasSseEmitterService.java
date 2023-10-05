package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import com.siliconvalley.domain.sse.repository.DemoCanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Slf4j
@RequiredArgsConstructor
public class CanvasSseEmitterService {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    private final DemoCanvasSseEmitterRepository demoCanvasSseEmitterRepository;
    private final CanvasSseEmitterCreater canvasSseEmitterCreater;

    public SseEmitter connect(Long profileId) {

        SseEmitter preSseEmitters = canvasSseEmitterRepository.findById(profileId);
        if (preSseEmitters != null) {
            canvasSseEmitterRepository.delete(profileId);
        }
        SseEmitter sseEmitter = canvasSseEmitterCreater.createEmitter(profileId);
        log.info(profileId + "번 유저 SSE 연결 완료");
        return sseEmitter;
    }

    public SseEmitter connect(String tempId){

        SseEmitter preSseEmitters = demoCanvasSseEmitterRepository.findById(tempId);
        if (preSseEmitters != null) {
            demoCanvasSseEmitterRepository.delete(tempId);
        }
        SseEmitter sseEmitter = canvasSseEmitterCreater.createEmitter(tempId);

        return sseEmitter;
    }
}