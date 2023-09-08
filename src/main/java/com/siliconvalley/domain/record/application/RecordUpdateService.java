package com.siliconvalley.domain.record.application;

import com.siliconvalley.domain.record.code.RecordCode;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordUpdateService {

    private final RecordFindDao recordFindDao;

    public Response updateRecord(Long recordId, RecordUpdateRequest dto) {
        Record record = recordFindDao.findById(recordId);
        record.updateScore(dto.getScore());
        return Response.of(RecordCode.UPDATE_SUCCESS, null);
    }
}
