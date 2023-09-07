package com.siliconvalley.domain.item.item.application;

import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.item.stage.dao.StageRepository;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.item.dto.SubjectItemCreateRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectItemCreateService {

    private final ItemRepository itemRepository;

    public Response createSubjectItem(SubjectItemCreateRequest dto) {
        Item item = dto.getItem();

        // Item과 Subject빌드 및 연관관계 매핑
        item.setSubject(dto.getSubject());

        // Item이 저장될 때 Subject 자동 저장
        itemRepository.save(item);

        return Response.of(ItemCode.CREATE_SUCCESS, new ItemPostSuccessResponse(item));
    }
}
