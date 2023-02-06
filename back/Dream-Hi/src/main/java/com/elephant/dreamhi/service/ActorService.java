package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorListResponseDto;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorProfileRequestDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActorService {

    private final ActorRepository actorRepository;


    /**
     * ActorList 조회 - With Filtering 메소드
     *
     * @param condition        : filtering 조건 Dto
     * @param pageable         : paging 정보
     * @param principalDetails : 현재 접근중인 주체
     * @return Page<ActorListResponseDto> with follow 정보
     */
    public Page<ActorListResponseDto> findActorsByFilter(ActorSearchCondition condition, Pageable pageable, PrincipalDetails principalDetails) {
        condition.setId(principalDetails.getId());
        Page<ActorListResponseDto> actorListResponseDto = actorRepository.findActorsWithFiltering(condition, pageable);
        log.info("{}", actorListResponseDto);
        if ((condition.getId() != 0L && condition.getIsFollow()) || condition.getId() == 0L) {
            return actorListResponseDto;
        }

        return actorListResponseDto;
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
    public ActorProfileDetailDto findActorProfileDetail(Long id, PrincipalDetails principalDetails) throws NotFoundException, VisibleException {
        ActorProfile profile = actorRepository.findActorProfileDetailByUser_Id(id).orElseThrow(() -> {
            return new NotFoundException("배우 프로필이 존재하지 않습니다.");
        });

        checkPrivateProfile(principalDetails, profile);

        return new ActorProfileDetailDto(profile);
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
        if (profile.getVisible() || profile.getUser().getId().equals(principalId)) {
            return true;
        }
        throw new VisibleException("비공개 프로필입니다.");
    }

    /**
     * 배우 프로필 공개/비공개 전환 메소드
     *
     * @param id : userId
     */
    @Transactional
    public void changeVisibleProfile(Long id) {
        ActorProfile actorProfile = actorRepository.findByUser_Id(id)
                                                   .orElseThrow(() -> new UsernameNotFoundException(id + " 유저가 존재하지 않습니다."));
        actorProfile.changeVisible();
    }

    /**
     * User, Actor 프로필 정보 변경 메소드
     */
    @Transactional
    public void updateActorProfile(Long userId, ActorProfileRequestDto actorProfileRequestDto) throws AccessDeniedException {
        ActorProfile actorProfile = actorRepository.findByIdAndUser_Id(actorProfileRequestDto.getActorProfileId(), userId)
                                                   .orElseThrow(() -> new AccessDeniedException("수정 권한이 없습니다."));
        User user = actorProfile.getUser();
        user.changeName(actorProfileRequestDto.getName());
        actorProfile.changeActorProfileInfo(actorProfileRequestDto);
    }

}
