package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.MyFollowersDto;
import java.util.List;

public interface FolloweReositoryCustom {
    List<MyFollowersDto> findAllByActor_Id(Long actorId);

}
