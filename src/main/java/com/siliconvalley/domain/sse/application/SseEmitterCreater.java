package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class SseEmitterCreater {

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private Long EXPIRATION_TIME;

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter createEmitter(String id) {


        SseEmitter sseEmitter = new SseEmitter(EXPIRATION_TIME);
        sseEmitterRepository.save(id, sseEmitter);

        // 연결 실패시 삭제
        sseEmitter.onCompletion(() -> sseEmitterRepository.delete(id));
        sseEmitter.onTimeout(() -> sseEmitterRepository.delete(id));

        return sseEmitter;
    }
}
