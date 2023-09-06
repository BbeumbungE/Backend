package com.siliconvalley.domain.item.sketch.dao;

import com.siliconvalley.domain.item.sketch.dto.SketchResponseList;
import com.siliconvalley.domain.item.sketch.domain.Sketch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SketchFindDao {

    private final SketchRepository sketchRepository;

    public SketchResponseList getAllsketches(Long subjectId) {
        List<Sketch> sketchList = sketchRepository.findBySubjectId(subjectId);
        return new SketchResponseList(sketchList);
    }
}
