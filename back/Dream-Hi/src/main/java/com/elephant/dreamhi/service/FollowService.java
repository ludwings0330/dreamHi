package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
import com.elephant.dreamhi.model.statics.FollowType;
import com.elephant.dreamhi.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public Long getFollowerCount(Long id) {
        return followRepository.countByActor_Id(id);
    }

    @Transactional(readOnly = true)
    public Boolean checkFollow(FollowType type, Long id, Long followerId) {
        return followRepository.checkFollow(type, id, followerId).isPresent();
    }

}
