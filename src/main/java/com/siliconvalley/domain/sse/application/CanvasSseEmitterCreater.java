package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
@Slf4j
public class CanvasSseEmitterCreater {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    public static final long EXPIRATION_TIME = 600000;
    public SseEmitter createEmitter(Long profileId) {

        SseEmitter sseEmitter = new SseEmitter(EXPIRATION_TIME);
        canvasSseEmitterRepository.save(profileId, sseEmitter);

        // 연결 실패시 삭제
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            canvasSseEmitterRepository.delete(profileId);
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            canvasSseEmitterRepository.delete(profileId);
        });
        sseEmitter.onError((e) -> {
            log.info("on" + e.getMessage() + " callback");
            canvasSseEmitterRepository.delete(profileId);
        });

        return sseEmitter;
    }
}
