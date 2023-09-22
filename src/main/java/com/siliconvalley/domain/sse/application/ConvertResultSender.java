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

    public void send(SseEmitter sseEmitter, Object data, Long profileId, String eventName) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(data)
                    .reconnectTime(0L)); // 재연결 시도
        } catch (IOException exception) {
            canvasSseEmitterRepository.delete(profileId);
            throw new RuntimeException("SSE Connect Fail");
        }
    }

}
