package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.dto.CastingSaveDto;
import com.elephant.dreamhi.model.dto.CastingUpdateDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Casting;
import com.elephant.dreamhi.repository.CastingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CastingServiceImpl implements CastingService {

    private final StyleService styleService;
    private final CastingRepository castingRepository;

    /**
     * @param announcementId 공고ID
     * @return 공고ID로 조회한 공고에서 모집하는 배역들의 정보
     */
    @Override
    public List<CastingDetailDto> findCastingDetails(Long announcementId) {
        return castingRepository.findByAnnouncementId(announcementId);
    }

    /**
     * @param announcement   DB에 저장된 공고
     * @param castingSaveDto 저장할 배역 정보
     * @throws NotFoundException 존재하지 않는 스타일 ID를 전달받은 경우 발생하는 예외
     */
    @Override
    @Transactional
    public void saveCasting(Announcement announcement, CastingSaveDto castingSaveDto) throws NotFoundException {
        Casting casting = Casting.toEntity(announcement, castingSaveDto);
        castingRepository.save(casting);
        styleService.saveStyleRelations(casting, castingSaveDto.getStyles());
    }

    /**
     * @param announcement     DB에 저장된 공고
     * @param castingUpdateDto 수정할 배역 정보
     * @throws NotFoundException 존재하지 않는 스타일 ID를 전달받은 경우 발생하는 예외
     */
    @Override
    public void updateCasting(Announcement announcement, CastingUpdateDto castingUpdateDto) throws NotFoundException {
        Casting casting = castingRepository.findById(castingUpdateDto.getId())
                                           .orElseThrow(() -> new NotFoundException("해당 배역을 찾을 수 없습니다."));
        casting.changeCasting(castingUpdateDto);
        styleService.updateStyleRelations(casting, castingUpdateDto.getStyles());
    }

}
