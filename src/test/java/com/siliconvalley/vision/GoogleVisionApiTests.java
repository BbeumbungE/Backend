package com.siliconvalley.vision;

import com.siliconvalley.domain.google.service.GoogleVisionApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName(value = "Google Vision API에 대한 실제 구동 테스트")
public class GoogleVisionApiTests {

    @Autowired
    private GoogleVisionApiService googleVisionApiService;

    @Test
    @DisplayName(value = "visionAPI를 활용해서 사진의 라벨을 소문자로 추출하고, detecting confidence를 추출한다.")
    void 라벨_추출_테스트(){

        // given
        String imageUrl = "https://aicanvas-mw.s3.ap-northeast-2.amazonaws.com/profile/36/canvas/handbag/1696378614753525592.JPG";

        // when
        Map<String, Float> results = googleVisionApiService.detectLabels(imageUrl);

        // then
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("bag"));
        assertTrue(results.keySet().stream().allMatch(key -> key.equals(key.toLowerCase())));
    }

}
