package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.repository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class SseEmitterSender {

    private final SseEmitterRepository sseEmitterRepository;
    private static final int RECONNECT_TIME_MILLIS = 0;

    public void send(SseEmitter sseEmitter, String id, Object data, Long profileId, String name) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .name(name)
                    .data(data)
                    .reconnectTime(RECONNECT_TIME_MILLIS));

        } catch (IOException exception) {
            sseEmitterRepository.delete(profileId);
        }
    }
}
