package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.dto.SubjectItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectCreateService {

    private final ItemRepository itemRepository;

    public ItemPostSuccessResponse createSubjectItem(SubjectItemCreateRequest dto) {
        Item item = dto.toItemEntity();
        Subject subject = dto.toSubjectEntity();

        // Item과 Subject 객체를 연결
        item.setSubject(subject);

        // Item이 저장될 때 Subject 자동 저장
        itemRepository.save(item);

        return new ItemPostSuccessResponse(item);
    }
}
