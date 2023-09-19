package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class CanvasSseEmitterFInder {
    private final CanvasSseEmitterRepository canvasSseEmitterRepository;

    public SseEmitter findByProfileId(Long profileId){
       return canvasSseEmitterRepository.findById(profileId);
    }
}
