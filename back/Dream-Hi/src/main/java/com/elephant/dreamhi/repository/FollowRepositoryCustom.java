package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.model.statics.FollowType;
import java.util.List;
import java.util.Optional;

public interface FollowRepositoryCustom {
    List<MyFollowersDto> findAllByActor_Id(Long actorId);

    Optional<Follow> checkFollow(FollowType type, Long id, Long followerId);

}
