package com.siliconvalley.domain.sse.api;

import com.siliconvalley.domain.sse.application.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SseConnectApi {

    private final SseEmitterService sseEmitterService;

    /**
     * SSE Connect
     **/
    @GetMapping("/connects/profiles/{profileId}")
    public SseEmitter connect(
            @PathVariable(name = "profileId") Long profileId,
            @RequestHeader(value = "Last-Event_ID", required = false, defaultValue = "") String lastEventId
    )
    {
        return sseEmitterService.connect(profileId, lastEventId);
    }
}
