package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSaveDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.AnnouncementUpdateDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.FollowRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.ProducerRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final ProcessService processService;
    private final CastingService castingService;
    private final FollowRepository followRepository;
    private final ProcessRepository processRepository;
    private final ProducerRepository producerRepository;
    private final AnnouncementRepository announcementRepository;

    /**
     * @param searchCondition 필터, 검색어 등의 검색 조건
     * @param pageable        페이지네이션을 위한 정보
     * @param user            현재 유저
     * @return 페이지네이션이 완료된 공고 목록에서의 정보를 담은 DTO 목록
     */
    @Override
    public Page<AnnouncementSimpleDto> findList(AnnouncementSearchCondition searchCondition, Pageable pageable, PrincipalDetails user) {
        Page<AnnouncementSimpleDto> announcementSimpleDtos = announcementRepository.findAllByCondition(searchCondition, pageable, user.getId());

        announcementSimpleDtos.forEach(dto -> {
            ProcessStageDto state = processService.findProcessAndStage(dto.getId(), user);
            dto.setState(state);
        });

        return announcementSimpleDtos;
    }

    /**
     * 공고 상세 조회 전에 공고의 조회수를 1 증가시킨다.
     *
     * @param announcementId 공고ID
     * @param user           인증 객체로부터 얻은 유저 정보
     * @return 공고의 상세 내용을 반환
     * @throws NotFoundException 공고가 존재하지 않는 경우
     */
    @Override
    @Transactional
    public AnnouncementDetailDto findDetail(Long announcementId, PrincipalDetails user) throws NotFoundException {
        announcementRepository.plusHitByAnnouncementId(announcementId);
        return announcementRepository.findByAnnouncementIdAndFollowerId(announcementId, user.getId())
                                                                            .orElseThrow(() -> new NotFoundException("해당 공고를 찾을 수 없습니다."));
    }

    /**
     * @param announcementSaveDto 등록할 공고의 정보
     * @throws NotFoundException 잘못된 스타일 ID를 전달받은 경우 발생하는 예외
     */
    @Override
    @Transactional
    public void saveAnnouncement(AnnouncementSaveDto announcementSaveDto) throws NotFoundException {
        Producer producer = producerRepository.getReferenceById(announcementSaveDto.getProducerId());
        Announcement announcement = Announcement.toEntity(announcementSaveDto, producer);
        announcementRepository.save(announcement);

        // 공고를 처음 등록할 때, Process도 함께 설정한다.
        processRepository.save(Process.getInstanceForRecruiting(announcement));

        announcementSaveDto.getCastings()
                           .forEach(castingSaveDto -> castingService.saveCasting(announcement, castingSaveDto));
    }

    /**
     * @param announcementUpdateDto 수정할 공고의 정보
     * @throws NotFoundException 존재하지 않는 스타일 ID를 전달받은 경우 발생하는 예외
     */
    @Override
    @Transactional
    public void updateAnnouncement(AnnouncementUpdateDto announcementUpdateDto) throws NotFoundException {
        Announcement announcement = announcementRepository.findById(announcementUpdateDto.getId())
                                                          .orElseThrow(() -> new NotFoundException("해당 공고를 찾을 수 없습니다."));
        announcement.changeAnnouncement(announcementUpdateDto);

        announcementUpdateDto.getCastings()
                             .forEach(castingUpdateDto -> castingService.updateCasting(announcement, castingUpdateDto));
    }

    @Override
    @Transactional
    public void deleteAnnouncement(Long announcementId) {
        announcementRepository.deleteById(announcementId);
    }

}
