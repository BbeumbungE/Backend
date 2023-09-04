package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.item.subject.dto.SubjectItemCreateRequest;
import com.siliconvalley.domain.item.subject.dao.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectCreateService {

    private final ItemRepository itemRepository;
    private final SubjectRepository subjectRepository;

    public ItemPostSuccessResponse createSubjectItem(SubjectItemCreateRequest dto) {
        Item item = itemRepository.save(dto.toItemEntity());
        subjectRepository.save(dto.toSubjectEntity(item));
        return new ItemPostSuccessResponse(item);
    }
}
