package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventCashFinder {

    private final EventCashRepository eventCashRepository;

    public Map<String, NotificationResponse> findAllById (String profileId) {
        return eventCashRepository.findAllById(profileId);
    }
}
