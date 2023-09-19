package com.siliconvalley.domain.sse.repository;

import com.siliconvalley.domain.sse.exception.SseNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class SseEmitterRepository {

    private final Map<Long, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    public void save(Long id, SseEmitter sseEmitter) {
        sseEmitterMap.put(id, sseEmitter);
    }

    public void delete(Long id) {
        sseEmitterMap.remove(id);
    }

    public SseEmitter findById(Long id) {
        return sseEmitterMap.get(id);
    }

    public SseEmitter findByIdWithExceptionHandling(Long id) {
        SseEmitter sseEmitter = sseEmitterMap.get(id);
        if (sseEmitter == null) {
            throw new SseNotFoundException(id.toString());
        }
        return sseEmitter;
    }
    public Map<Long, SseEmitter> findAll() {
        return sseEmitterMap;
    }

}
