package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SseEmitterFinder {

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter findByProfileId(Long profileId) {
        return sseEmitterRepository.findById(profileId);
    }

    public Map<Long, SseEmitter> findAll() {
        return sseEmitterRepository.findAll();
    }
}
