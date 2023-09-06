package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.code.EmotionCode;
import com.siliconvalley.domain.post.domain.EmotionType;
import com.siliconvalley.domain.post.dto.EmotionTypeResponse;
import com.siliconvalley.domain.post.exception.EmotionTypeNotFoundException;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmotionTypeFindDao {

    private final EmotionTypeRepository emotionTypeRepository;

    public EmotionType findById(Long emotionTypeId){
        Optional<EmotionType> emotionType = emotionTypeRepository.findById(emotionTypeId);
        emotionType.orElseThrow(() -> new EmotionTypeNotFoundException(emotionTypeId + "번 감정표현타입"));
        return emotionType.get();
    }

    public List<EmotionType> getAllEmotionType(){
        List<EmotionType> emotionTypes = emotionTypeRepository.findAll();
        return emotionTypes;
    }

}
