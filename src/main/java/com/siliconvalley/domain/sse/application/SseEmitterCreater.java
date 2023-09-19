package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
@Slf4j
public class SseEmitterCreater {

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private Long EXPIRATION_TIME;

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter createEmitter(Long profileId) {

        SseEmitter sseEmitter = new SseEmitter(EXPIRATION_TIME);
        sseEmitterRepository.save(profileId, sseEmitter);

        // 연결 실패시 삭제
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            sseEmitterRepository.delete(profileId);
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitterRepository.delete(profileId);
        });
        sseEmitter.onError((e) -> {
            log.info("on" + e.getMessage() + " callback");
            sseEmitterRepository.delete(profileId);
        });

        return sseEmitter;
    }
}
