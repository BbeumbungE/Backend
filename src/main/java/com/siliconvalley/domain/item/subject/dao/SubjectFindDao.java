package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.exception.SubjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectFindDao {

    private final SubjectRepository subjectRepository;

    public Subject findById(Long subjectId) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        subjectOptional.orElseThrow(() -> new SubjectNotFoundException(subjectId));
        return subjectOptional.get();
    }

}
