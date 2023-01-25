package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.repository.ActorRepository;
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

}
