package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.dto.SketchResponseList;
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
