package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorListResponseDto;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorProfileRequestDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.statics.FollowType;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.FollowRepository;
import java.util.HashSet;
import java.util.Set;
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

    private final FollowRepository followRepository;
    private final ActorRepository actorRepository;


    /**
     * ActorList 조회 - With Filtering 메소드
     *
     * @param condition : filtering 조건 Dto
     * @param pageable  : paging 정보
     * @return Page<ActorListResponseDto> with follow 정보
     */
    public Page<ActorListResponseDto> findActorsByFilter(ActorSearchCondition condition, Pageable pageable) {
        Page<ActorListResponseDto> actorListResponseDto = actorRepository.findActorsWithFiltering(condition, pageable);

        if (condition.getId() != 0L && Boolean.TRUE.equals(condition.getIsFollow())) {
            actorListResponseDto.getContent()
                                .forEach(item -> item.setIsFollow(true));
        } else if (condition.getId() != 0L && Boolean.FALSE.equals(condition.getIsFollow())) {
            Set<Long> actorFollowInfo = new HashSet<>(followRepository.findActorIdByFollowerId(condition.getId(), FollowType.ACTOR));
            actorListResponseDto.getContent()
                                .forEach(item -> item.setIsFollow(actorFollowInfo.contains(item.getUserId())));
        }

        return actorListResponseDto;
    }

    /**
     * 배우 프로필 상세 정보 - User x ActorProfile x Style 정보 조회
     *
     * @param actorId : 조회할 배우의 userId
     * @param userId  : 현재 접근중인 주체의 userId
     * @return ActorProfileDetailDto
     * @throws NotFoundException : id에 해당하는 프로필이 존재하지 않는 경우 발생합니다.
     * @throws VisibleException  : 해당 프로필이 비공개일 경우 예외를 발생합니다.
     */
    public ActorProfileDetailDto findActorProfileDetail(Long actorId, Long userId) throws NotFoundException, VisibleException {
        ActorProfile profile = actorRepository.findActorProfileDetailByUser_Id(actorId)
                                              .orElseThrow(() -> new NotFoundException(actorId + "번 유저의 배우 프로필이 존재하지 않습니다."));
        if (!profile.getVisible() && !actorId.equals(userId)) throw new VisibleException("비공개 프로필입니다.");
        return new ActorProfileDetailDto(profile);
        }

        /**
         * 배우 프로필 공개/비공개 전환 메소드
         *
         * @param id : userId
         */
        @Transactional
        public void changeVisibleProfile (Long id){
            ActorProfile actorProfile = actorRepository.findByUser_Id(id)
                                                       .orElseThrow(() -> new UsernameNotFoundException(id + "번 유저가 존재하지 않습니다."));
            actorProfile.changeVisible();
        }

        /**
         * User, Actor 프로필 정보 변경 메소드
         */
        @Transactional
        public void updateActorProfile (Long userId, ActorProfileRequestDto actorProfileRequestDto) throws AccessDeniedException {
            ActorProfile actorProfile = actorRepository.findByIdAndUser_Id(actorProfileRequestDto.getActorProfileId(), userId)
                                                       .orElseThrow(() -> new AccessDeniedException("수정 권한이 없습니다."));
            User user = actorProfile.getUser();
            user.changeName(actorProfileRequestDto.getName());
            actorProfile.changeActorProfileInfo(actorProfileRequestDto);
        }

        private static boolean checkDoFollowProcess (ActorSearchCondition condition){
            return condition.getId() == 0L || Boolean.TRUE.equals(condition.getIsFollow());
        }

    }
