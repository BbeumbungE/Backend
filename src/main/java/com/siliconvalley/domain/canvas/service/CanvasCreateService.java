package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CanvasCreateService {

    public Canvas createCanvas(CanvasCreateDto dto){
        // 임시!! 후에 AI 서버 연결하면 코드 추가
        return dto.toEntity();
    }

}
