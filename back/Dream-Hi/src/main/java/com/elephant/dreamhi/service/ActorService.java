package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.entity.Filmography;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.repository.ActorProfileMediaFileRepository;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.FilmographyRepository;
import com.elephant.dreamhi.repository.FollowRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final FollowRepository followRepository;
    private final FilmographyRepository filmographyRepository;
    private final ActorProfileMediaFileRepository actorProfileMediaFileRepository;

    public Page<ActorSimpleProfileDto> findActorsByFilter(ActorSearchCondition condition, Pageable pageable) {
        condition.setId(1L);

        final Page<ActorProfile> profiles = actorRepository.findActorSimpleProfiles(condition, pageable);
        final Page<ActorSimpleProfileDto> profileDtos = profiles.map(p -> new ActorSimpleProfileDto(p, condition.getId()));

        return profileDtos;
    }

    public ActorProfileDetailDto findActorProfileById(Long id) {

        final Long loginId = 1L;

        final Optional<ActorProfile> profile = actorRepository.findActorProfileById(id);
        final ActorProfile actorProfile = profile.orElseThrow();

        final Optional<Follow> followInfo = followRepository.findByActor_IdAndFollower_Id(actorProfile.getUser().getId(), loginId);
        boolean isFollow = followInfo.isPresent();

        final List<Filmography> filmographies = filmographyRepository.findAllByActorProfile_Id(actorProfile.getId());
        final List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(actorProfile.getId());

        ActorProfileDetailDto response = new ActorProfileDetailDto(actorProfile, filmographies, mediaFiles, isFollow);

        return response;
    }

}
