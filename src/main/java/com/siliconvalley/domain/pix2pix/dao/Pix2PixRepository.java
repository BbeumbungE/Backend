package com.siliconvalley.domain.pix2pix.dao;

import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pix2PixRepository extends JpaRepository<Pix2Pix, Long> {
    Pix2Pix findBySubjectId(Long subjectId);
}
