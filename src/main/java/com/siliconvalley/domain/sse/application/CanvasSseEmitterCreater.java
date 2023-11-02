package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import com.siliconvalley.domain.sse.repository.DemoCanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
@Slf4j
public class CanvasSseEmitterCreater {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    private final DemoCanvasSseEmitterRepository demoCanvasSseEmitterRepository;
    public static final long EXPIRATION_TIME = 120000;
    public static final long DEMO_EXPIRATION_TIME = 120000;
    public SseEmitter createEmitter(Long profileId) {

        SseEmitter sseEmitter = new SseEmitter(EXPIRATION_TIME);
        canvasSseEmitterRepository.save(profileId, sseEmitter);

        // 연결 실패시 삭제
        sseEmitter.onCompletion(() -> {
            canvasSseEmitterRepository.delete(profileId);
        });
        sseEmitter.onTimeout(() -> {
            canvasSseEmitterRepository.delete(profileId);
        });
        sseEmitter.onError((e) -> {
            canvasSseEmitterRepository.delete(profileId);
        });

        return sseEmitter;
    }

    public SseEmitter createEmitter(String tempId){

        SseEmitter sseEmitter = new SseEmitter(DEMO_EXPIRATION_TIME);
        demoCanvasSseEmitterRepository.save(tempId, sseEmitter);

        // 연결 실패시 삭제
        sseEmitter.onCompletion(() -> {
            demoCanvasSseEmitterRepository.delete(tempId);
        });
        sseEmitter.onTimeout(() -> {
            demoCanvasSseEmitterRepository.delete(tempId);
        });
        sseEmitter.onError((e) -> {
            demoCanvasSseEmitterRepository.delete(tempId);
        });

        return sseEmitter;
    }
}
