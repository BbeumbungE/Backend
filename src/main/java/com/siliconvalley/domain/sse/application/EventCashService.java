package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventCashService {

    private final EventCashRepository eventCashRepository;
    private final EventCashFinder eventCashFinder;
    private final SseEmitterSender sseEmitterSender;

    public void sendNotReceivedEvent(Long profileId, String lastEventId, SseEmitter sseEmitter) {
        if (!lastEventId.isEmpty()) {
            Map<String, NotificationResponse> events = eventCashFinder.findAllById(String.valueOf(profileId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> {
                        sseEmitterSender.send(sseEmitter, entry.getKey(), entry.getValue(), profileId, "alarm");
                        eventCashRepository.delete(entry.getKey()); // 송신 후 삭제
                    });
        }
    }
}
