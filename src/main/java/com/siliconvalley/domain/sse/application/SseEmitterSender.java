package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SseEmitterSender {

    private final SseEmitterRepository sseEmitterRepository;

    public void send(SseEmitter sseEmitter, String id, Object data, Long profileId, String name) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .name(name)
                    .data(data)
                    .reconnectTime(0));

        } catch (IOException exception) {
            sseEmitterRepository.delete(profileId);
            throw new RuntimeException(SseCode.CONNECT_FAIL.getMessage());
        }
    }

    public void sendNotReceivedEvent(String lastEventId) {

    }


}
