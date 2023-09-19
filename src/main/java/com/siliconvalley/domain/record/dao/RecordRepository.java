package com.siliconvalley.domain.record.dao;

import com.siliconvalley.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> findByProfileIdAndStageId(Long profileId, Long stageId);
    Optional<Record> findByProfileId(Long profileId);
}
