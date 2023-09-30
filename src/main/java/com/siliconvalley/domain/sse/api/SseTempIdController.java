package com.siliconvalley.domain.sse.api;

import com.siliconvalley.domain.sse.application.TempIdCreateService;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SseTempIdController {

    private final TempIdCreateService tempIdCreateService;

    @PostMapping("/tempId")
    public ResponseEntity<Response> createTempId(){
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(CommonCode.SUCCESS_CREATE, tempIdCreateService.createTempIdForSse()));
    }
}
