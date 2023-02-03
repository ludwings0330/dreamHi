package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ProcessService processService;

    /**
     *
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
     *
     * @param announcementId 공고ID
     * @param user           인증 객체로부터 얻은 유저 정보
     * @return 공고의 상세 내용을 반환
     * @throws NotFoundException 공고가 존재하지 않는 경우
     */
    @Override
    public AnnouncementDetailDto findDetail(Long announcementId, PrincipalDetails user) throws NotFoundException {
        return announcementRepository.findByAnnouncementIdAndFollowerId(announcementId, user.getId())
                                     .orElseThrow(() -> new NotFoundException("해당 공고를 찾을 수 없습니다."));
    }

}
