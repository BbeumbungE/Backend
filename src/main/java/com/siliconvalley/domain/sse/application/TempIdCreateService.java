package com.siliconvalley.domain.sse.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TempIdCreateService {

    private final CanvasSseEmitterFinder canvasSseEmitterFinder;

    public String createTempIdForSse(){
        String tempId = UUID.randomUUID().toString();
        while (canvasSseEmitterFinder.findByTempId(tempId) != null){
            tempId = UUID.randomUUID().toString();
        }
        return tempId;
    }

}
