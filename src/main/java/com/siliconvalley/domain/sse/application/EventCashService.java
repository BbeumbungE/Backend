package com.siliconvalley.domain.sse.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventCashService {

    private final EventCashFinder eventCashFinder;
    private final SseEmitterSender sseEmitterSender;

    public void sendNotReceivedEvent(String profileId, String lastEventId, SseEmitter sseEmitter) {
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = eventCashFinder.findAllById(profileId);
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sseEmitterSender.send(sseEmitter, entry.getKey(), entry.getValue()));
        }
    }
}
