package com.siliconvalley.domain.sse.api;

import com.siliconvalley.domain.sse.application.TempIdCreateService;
import com.siliconvalley.domain.sse.dto.CreateTempIdRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SseTempIdController {

    private final TempIdCreateService tempIdCreateService;

    @PostMapping("/tempId")
    public ResponseEntity<Response> createTempId(
            @RequestBody CreateTempIdRequest dto
            ){
        Response response = tempIdCreateService.getSubjectImageAndCreateTempId(dto.getSubjectId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
