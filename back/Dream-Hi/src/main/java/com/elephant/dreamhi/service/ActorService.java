package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Page<ActorSimpleProfileDto> findActorsByFilter(ActorSearchCondition condition, Pageable pageable) {
        condition.setId(1L);

        final Page<ActorProfile> profiles = actorRepository.findActorSimpleProfiles(condition, pageable);
        final Page<ActorSimpleProfileDto> profileDtos = profiles.map(p -> new ActorSimpleProfileDto(p, condition.getId()));

        return profileDtos;
    }

    /**
     * 배우 프로필 상세 정보 - User x ActorProfile x Style 정보 조회
     *
     * @param id               : userId
     * @param principalDetails : 현재 접근중인 주체
     * @return ActorProfileDetailDto
     * @throws NotFoundException : id에 해당하는 프로필이 존재하지 않는 경우 발생합니다.
     * @throws VisibleException  : 해당 프로필이 비공개일 경우 예외를 발생합니다.
     */
    @Transactional
    public ActorProfileDetailDto findActorProfileDetail(Long id, PrincipalDetails principalDetails) throws NotFoundException, VisibleException {
        ActorProfile profile = actorRepository.findActorProfileByUser_Id(id).orElseThrow(() -> {
            return new NotFoundException();
        });

        checkPrivateProfile(principalDetails, profile);

        ActorProfileDetailDto response = new ActorProfileDetailDto(profile);
        return response;
    }

    /**
     * 비공개 프로필 여부 체크 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @param profile          : 전달받은 id를 기준으로 가져온 profile 정보
     * @return true : 열람 가능한 프로필이라면 true를 반환합니다.
     * @throws VisibleException : 비공개 프로필이면서 내 프로필이 아니라면 볼 수 없으니 VisibleException 발생합니다.
     */
    private static boolean checkPrivateProfile(PrincipalDetails principalDetails, ActorProfile profile) throws VisibleException {
        Long principalId = principalDetails.getId();
        if (profile.getVisible() == true || profile.getUser().getId().equals(principalId)) {
            return true;
        }
        throw new VisibleException("비공개 프로필입니다.");
    }

}
