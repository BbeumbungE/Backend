package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class SseEmitterFinder {

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter findById(String id) {
        return sseEmitterRepository.findById(id);
    }
}
