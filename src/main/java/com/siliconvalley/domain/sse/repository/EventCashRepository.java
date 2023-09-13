package com.siliconvalley.domain.sse.repository;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EventCashRepository {

    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    public void save(String id, Object event) {
        eventCache.put(id, event);
    }

    public void delete(String id) {
        eventCache.remove(id);
    }

    public Map<String, Object> findAllById(String profileId) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(profileId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
