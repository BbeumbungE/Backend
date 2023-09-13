package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SseEmitterSender {

    private final SseEmitterRepository sseEmitterRepository;

    public void send(SseEmitter sseEmitter, String id, Object data) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            sseEmitterRepository.delete(id);
            throw new RuntimeException("SSE Connect Fail");
        }
    }

    public void sendNotReceivedEvent(String lastEventId) {

    }


}
