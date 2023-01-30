package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.elephant.dreamhi.model.entity.Follow;
import java.util.List;
import java.util.Optional;

public interface FollowRepositoryCustom {
    List<MyFollowersDto> findAllByActor_Id(Long actorId);

    Optional<Follow> checkFollow(FollowRequestDto followRequestDto, Long followerId);

}
