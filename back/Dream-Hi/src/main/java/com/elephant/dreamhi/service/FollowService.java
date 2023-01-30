package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
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

    @Transactional
    public Long getFollowerCount(Long id) {
        return followRepository.countByActor_Id(id);
    }

    @Transactional
    public Boolean checkFollow(FollowRequestDto followRequestDto, Long followerId) {
        return followRepository.checkFollow(followRequestDto, followerId).isPresent();
    }

}
