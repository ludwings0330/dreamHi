package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.repository.CastingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CastingServiceImpl implements CastingService {

    private final CastingRepository castingRepository;

    @Override
    public List<CastingDetailDto> findCastingDetails(Long announcementId) {
        return castingRepository.findByAnnouncementId(announcementId);
    }

}
