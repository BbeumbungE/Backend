package com.siliconvalley.domain.pix2pix.dao;

import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import com.siliconvalley.domain.pix2pix.dto.UseModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class Pix2PixFindDao {

    private final Pix2PixRepository pix2PixRepository;

    @Cacheable(key = "'subject:'+ #subject", value = "subjectAndModelCache")
    public UseModelDto findBySubjectId(Long subjectId){
        Pix2Pix pix2Pix = pix2PixRepository.findBySubjectId(subjectId);
        return new UseModelDto(pix2Pix);
    }

}
