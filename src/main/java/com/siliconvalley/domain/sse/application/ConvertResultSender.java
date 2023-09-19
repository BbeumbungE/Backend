package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ConvertResultSender {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;

    public void send(SseEmitter sseEmitter, Object data, Long profileId) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            canvasSseEmitterRepository.delete(profileId);
            throw new RuntimeException("SSE Connect Fail");
        }
    }

}
