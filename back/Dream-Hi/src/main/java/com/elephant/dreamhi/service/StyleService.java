package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.FullResourceException;
import com.elephant.dreamhi.model.dto.ActorProfileRequestDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.ActorStyleRelationRepository;
import com.elephant.dreamhi.repository.StyleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StyleService {

    @Value("${app.style-tag-size}")
    private Integer styleTagSize;
    private final ActorRepository actorRepository;
    private final ActorStyleRelationRepository actorStyleRelationRepository;
    private final StyleRepository styleRepository;

    /**
     * 배우 스타일 태그 수정 - delete And save
     *
     * @param actorProfileRequestDto : request 데이터
     * @throws FullResourceException           : 최대 저장 개수 초과
     * @throws DataIntegrityViolationException : Unique 제약 조건 위반
     */
    @Transactional
    public void updateActorStyleTags(ActorProfileRequestDto actorProfileRequestDto) throws FullResourceException, DataIntegrityViolationException {
        List<ActorStyleRelation> actorStyleRelations = actorStyleRelationRepository.findByActorProfileId(actorProfileRequestDto.getActorProfileId());
        checkFullResource(actorStyleRelations.size(), actorProfileRequestDto.getDeleteStyles().size(),
                          actorProfileRequestDto.getInsertStyles().size());
        actorStyleRelationRepository.deleteAllInStlyeIdQuery(actorProfileRequestDto.getActorProfileId(), actorProfileRequestDto.getDeleteStyles());
        insertAllStyles(actorProfileRequestDto);
    }

    /**
     * tag insert 메소드
     *
     * @param actorProfileRequestDto : request 데이터
     * @throws DataIntegrityViolationException : Unique 제약 조건 위반
     */
    private void insertAllStyles(ActorProfileRequestDto actorProfileRequestDto) {
        List<ActorStyleRelation> newStyleRelations = new ArrayList<>();
        ActorProfile actorProfile = actorRepository.getReferenceById(actorProfileRequestDto.getActorProfileId());
        actorProfileRequestDto.getInsertStyles().forEach(isr -> {
            Style style = styleRepository.getReferenceById(isr);
            style.addCount();
            ActorStyleRelation asr = ActorStyleRelation.builder().actorProfile(actorProfile).style(style).build();
            newStyleRelations.add(asr);
        });
        actorStyleRelationRepository.saveAll(newStyleRelations);
    }

    /**
     * 저장 가능 여부 체크 메소드
     *
     * @param originSize : 기존 태그 개수
     * @param deleteSize : 삭제할 태그 개수
     * @param insertSize : 추가할 태그 개수
     * @throws FullResourceException : 저장 공간 부족 예외
     */
    private void checkFullResource(Integer originSize, Integer deleteSize, Integer insertSize) {
        if (originSize + insertSize - deleteSize > styleTagSize) {
            throw new FullResourceException("최대 저장 개수를 초과했습니다.");
        }
    }

}
