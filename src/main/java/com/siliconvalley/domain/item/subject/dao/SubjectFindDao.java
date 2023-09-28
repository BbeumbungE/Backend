package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.dto.SubjectCachingDto;
import com.siliconvalley.domain.item.subject.exception.SubjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectFindDao {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject findById(Long subjectId) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        subjectOptional.orElseThrow(() -> new SubjectNotFoundException(subjectId));
        return subjectOptional.get();
    }

    @Cacheable(key = "'subject:' + #subjectId", value = "subjectCache")
    public SubjectCachingDto findSubjectForCachingById(Long subjectId){
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        subject.orElseThrow(() -> new SubjectNotFoundException(subjectId));
        return new SubjectCachingDto(subject.get());
    }
}
