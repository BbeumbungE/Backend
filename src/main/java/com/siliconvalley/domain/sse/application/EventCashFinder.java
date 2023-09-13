package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.sse.repository.EventCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventCashFinder {

    private final EventCashRepository eventCashRepository;

    public Map<String, Object> findAllById (String profileId) {
        return eventCashRepository.findAllById(profileId);
    }
}
