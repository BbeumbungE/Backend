package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.exception.SketchNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SketchFindDao {

    private final SketchRepository sketchRepository;

    public List<Sketch> findSketchBySubjectId(Long subjectId){
        List<Sketch> sketches = sketchRepository.findBySubject_Id(subjectId);
        if (sketches.isEmpty()){
            throw new SketchNotFoundException(subjectId);
        }
        return sketches;
    }
}
