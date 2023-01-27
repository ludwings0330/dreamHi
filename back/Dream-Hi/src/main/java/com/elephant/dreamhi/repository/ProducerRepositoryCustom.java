package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.ProducerListResponseDto;
import com.elephant.dreamhi.model.dto.ProducerSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProducerRepositoryCustom {

    Page<ProducerListResponseDto> findProducersByCondition(ProducerSearchCondition condition, Pageable pageable);

}
