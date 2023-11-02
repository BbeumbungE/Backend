package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.CanvasSseEmitterRepository;
import com.siliconvalley.domain.sse.repository.DemoCanvasSseEmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConvertResultSender {

    private final CanvasSseEmitterRepository canvasSseEmitterRepository;
    private final DemoCanvasSseEmitterRepository demoCanvasSseEmitterRepository;
    private static final long RECONNECT_TIME = 300L;

    public void send(SseEmitter sseEmitter, Object data, Long profileId, String eventName) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(data)
                    .reconnectTime(RECONNECT_TIME)); // 재연결 시도
        } catch (IOException exception) {
            canvasSseEmitterRepository.delete(profileId);
            throw new RuntimeException("SSE Connect Fail");
        }
    }

    public void send(SseEmitter sseEmitter, Object data, String tempId, String eventName){
        try {
            sseEmitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(data));
        } catch (IOException exception) {
            demoCanvasSseEmitterRepository.delete(tempId);
            throw new RuntimeException("SSE Connect Fail");
        }
    }

}
