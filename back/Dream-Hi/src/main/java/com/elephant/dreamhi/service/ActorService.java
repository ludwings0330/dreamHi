package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.model.dto.FilmographyDto;
import com.elephant.dreamhi.model.dto.MediaFileDto;
import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.entity.Filmography;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.repository.ActorProfileMediaFileRepository;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.FilmographyRepository;
import com.elephant.dreamhi.repository.FollowRepository;
import com.elephant.dreamhi.repository.FolloweReositoryCustom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    //    private final FollowRepository followRepository;

    private final FolloweReositoryCustom followeReositoryCustom;
    private final FilmographyRepository filmographyRepository;
    private final ActorProfileMediaFileRepository actorProfileMediaFileRepository;

    public Page<ActorSimpleProfileDto> findActorsByFilter(ActorSearchCondition condition, Pageable pageable) {
        condition.setId(1L);

        final Page<ActorProfile> profiles = actorRepository.findActorSimpleProfiles(condition, pageable);
        final Page<ActorSimpleProfileDto> profileDtos = profiles.map(p -> new ActorSimpleProfileDto(p, condition.getId()));

        return profileDtos;
    }

//    public ActorProfileDetailDto findActorProfileById(Long id) {
//
//        final Long loginId = 1L;
//
//        final Optional<ActorProfile> profile = actorRepository.findActorProfileById(id);
//        final ActorProfile actorProfile = profile.orElseThrow();
//
//        final Optional<Follow> followInfo = followRepository.findByActor_IdAndFollower_Id(actorProfile.getUser().getId(), loginId);
//        boolean isFollow = followInfo.isPresent();
//
//        final List<Filmography> filmographies = filmographyRepository.findAllByActorProfile_Id(actorProfile.getId());
//        final List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(actorProfile.getId());
//
//        ActorProfileDetailDto response = new ActorProfileDetailDto(actorProfile, filmographies, mediaFiles, isFollow);
//
//        return response;
//    }

    /**
     * 배우 프로필 상세 정보 With  Style Tag 정보 조회
     *
     * @param id : ActorProfile.id
     * @return ActorProfileDetailDto
     * @throws NotFoundException : id에 해당하는 프로필이 존재하지 않는 경우 발생합니다.
     * @throws VisibleException    : 해당 프로필이 비공개일 경우 예외를 발생합니다.
     */
    public ActorProfileDetailDto findActorProfileWithStyleById(Long id) throws NotFoundException, VisibleException {
        ActorProfile profile = actorRepository.findActorProfileById(id).orElseThrow(() -> {
            return new NotFoundException();
        });
        if (profile.getVisible() == false) {
            throw new VisibleException("비공개 프로필입니다.");
        }
        ActorProfileDetailDto response = new ActorProfileDetailDto(profile);
        return response;
    }

    public FilmographyDto findFilmographiesByActorProfileId(Long id) {
        List<Filmography> filmographies = filmographyRepository.findAllByActorProfile_Id(id);
        FilmographyDto response = new FilmographyDto(id, filmographies);
        return response;
    }

    public MediaFileDto findMediaFilesByActorProfileId(Long id) {
        List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(id);
        MediaFileDto response = new MediaFileDto(id, mediaFiles);
        return response;
    }

    public List<MyFollowersDto> findFollowCount(Long id) {
        List<MyFollowersDto> myFollowers = followeReositoryCustom.findAllByActor_Id(id);
        myFollowers.forEach(f -> {
            System.out.println("f = " + f);
        });
//        return myFollowers.size();
        return myFollowers;
    }

}
