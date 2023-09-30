package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import com.siliconvalley.domain.sse.repository.DemoCanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class CanvasSseEmitterFinder {
    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    private final DemoCanvasSseEmitterRepository demoCanvasSseEmitterRepository;

    public SseEmitter findByProfileId(Long profileId){
       return canvasSseEmitterRepository.findById(profileId);
    }
    public SseEmitter findByTempId(String tempId) {return demoCanvasSseEmitterRepository.findById(tempId);}
}
