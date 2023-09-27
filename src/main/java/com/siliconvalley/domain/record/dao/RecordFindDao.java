package com.siliconvalley.domain.record.dao;

import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.exception.RecordNotFound;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordFindDao {

    private final RecordRepository recordRepository;

    public Record findById(Long recordId) {
        Optional<Record> recordOptional = recordRepository.findById(recordId);
        recordOptional.orElseThrow(() -> new RecordNotFound(recordId));
        return recordOptional.get();
    }

    public Optional<Record> findByProfileId(Long profileId) {
        return recordRepository.findByProfileId(profileId);
    }

    public Optional<Record> findByProfileIdAndStageId(Long profileId, Long stageId) {
        return recordRepository.findByProfileIdAndStageId(profileId, stageId);
    }

}
