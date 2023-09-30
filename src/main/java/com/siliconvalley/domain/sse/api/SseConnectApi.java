package com.siliconvalley.domain.sse.api;

import com.siliconvalley.domain.sse.application.CanvasSseEmitterService;
import com.siliconvalley.domain.sse.application.SseEmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SseConnectApi {

    private final SseEmitterService sseEmitterService;
    private final CanvasSseEmitterService canvasSseEmitterService;

    /**
     * Notification SSE Connect
     **/
    @GetMapping("/notifications/profiles/{profileId}")
    public SseEmitter connect(
            @PathVariable(name = "profileId") Long profileId,
            @RequestHeader(value = "Last-Event_ID", required = false, defaultValue = "") String lastEventId
    )
    {
        return sseEmitterService.connect(profileId, lastEventId);
    }

    /**
     * Canvas SSE Connect
     **/
    @GetMapping("canvases/profile/{profileId}")
    public SseEmitter connectSseForConvertedCanvas(
            @PathVariable Long profileId
    ){
        return canvasSseEmitterService.connect(profileId);
    }

    /**
     * Demo Canvas SSE Connect
     **/
    @GetMapping("canvases/demoId/{tempId}")
    public SseEmitter connectSseForConvertedDemoCanvas(
            @PathVariable String tempId
    ){
        return canvasSseEmitterService.connect(tempId);
    }

}
