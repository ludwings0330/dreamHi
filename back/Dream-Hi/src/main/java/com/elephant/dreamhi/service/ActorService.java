package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.repository.ActorRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Page<ActorSimpleProfileDto> findActorsByFilter(ActorSearchCondition filter, Pageable pageable) {
        return actorRepository.findActorSimpleProfiles(filter, pageable);
    }

    public ActorProfileDetailDto findActorProfileById(Long id) {
        // user 정보 가져오기 - name, email
        final Optional<ActorProfile> profile = actorRepository.findById(id);

        final ActorProfile actorProfile = profile.orElseThrow();// nosuchelementException

        return ActorProfileDetailDto.builder()
                                    .id(actorProfile.getId())
                                    .age(actorProfile.getAge())
                                    .description(actorProfile.getDescription())
                                    .gender(actorProfile.getGender())
                                    .height(actorProfile.getHeight())
                                    .title(actorProfile.getTitle())

                                    .build();
    }

}
