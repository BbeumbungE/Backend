package com.siliconvalley.domain.rabbitMQ.service;

import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenerateRoutingKeyService {

    private final SubjectFindDao subjectFindDao;
    public String generateRoutingKey(Long subjectId){
        Subject subject = subjectFindDao.findById(subjectId);
        return subject.getSubjectName() + ".*";
    }
}
