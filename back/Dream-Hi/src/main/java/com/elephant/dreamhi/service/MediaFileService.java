package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.FullResourceException;
import com.elephant.dreamhi.model.dto.MediaFileRequestDto;
import com.elephant.dreamhi.model.dto.MediaFileResponseDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.statics.MediaType;
import com.elephant.dreamhi.repository.ActorProfileMediaFileRepository;
import com.elephant.dreamhi.repository.ActorRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaFileService {

    @Value("${app.image-size}")
    private static Integer imageSize;
    @Value("${app.video-size}")
    private static Integer videoSize;

    private final ActorProfileMediaFileRepository actorProfileMediaFileRepository;
    private final ActorRepository actorRepository;

    public MediaFileResponseDto findMediaFilesByActorProfileId(Long id) {
        List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(id);
        return new MediaFileResponseDto(id, mediaFiles);
    }

    /**
     * 사진/영상 정보 추가 메소드
     *
     * @param userId              : 접근중인 주체
     * @param actorProfileId      : 조회할 배우 프로필
     * @param mediaFileRequestDto : RequestDto (파일 정보)
     * @throws AccessDeniedException : 권한 오류
     * @throws FullResourceException : 저장 공간 부족
     */
    @Transactional
    public Long addMediaFile(Long userId, Long actorProfileId, MediaFileRequestDto mediaFileRequestDto)
            throws AccessDeniedException, FullResourceException {
        ActorProfile actorProfile = actorRepository.findByIdAndUser_Id(actorProfileId, userId)
                                                   .orElseThrow(
                                                           () -> new AccessDeniedException(userId + " 유저는 " + actorProfileId + " 프로필 수정 권한이 없습니다."));

        checkStorageSize(mediaFileRequestDto, actorProfile);

        ActorProfileMediaFile mediaFile = mediaFileRequestDto.toEntity();
        mediaFile.changeActorProfile(actorProfile);

        return actorProfile.getId();
    }


    /**
     * 사진/영상 정보 삭제 메소드
     *
     * @param userId
     * @param actorProfileId
     * @param actorProfileMediaFileId
     * @throws AccessDeniedException          : 삭제 요청한 user가 잘못된 actorProfile을 수정하려고 접근할 때 발생
     * @throws EmptyResultDataAccessException : 삭제할 데이터가 존재하지 않다면 발생
     */
    @Transactional
    public void deleteMediaFile(Long userId, Long actorProfileId, Long actorProfileMediaFileId)
            throws AccessDeniedException, EmptyResultDataAccessException {
        Optional<ActorProfile> actorProfile = actorRepository.checkValidateModify(actorProfileId, userId);

        if (actorProfile.isEmpty()) {
            throw new AccessDeniedException(userId + " 유저는 " + actorProfileId + "번 프로필 삭제 권한이 없습니다.");
        }

        actorProfileMediaFileRepository.deleteById(actorProfileMediaFileId);
    }

    /**
     * 저장된 이미지/영상 개수를 조회하고 저장 공간이 남아있는지 확인하는 메소드
     *
     * @param mediaFileRequestDto : RequestDto
     * @param actorProfile        : 조회한 배우 프로필
     * @throws FullResourceException : 최대 개수라 저장할 수 없다면 CustomException 발생
     */
    private void checkStorageSize(MediaFileRequestDto mediaFileRequestDto, ActorProfile actorProfile) throws FullResourceException {
        List<ActorProfileMediaFile> actorProfileMediaFiles = actorProfile.getActorProfileMediaFiles();
        MediaType insertType = mediaFileRequestDto.getType();
        Integer cnt = (int) actorProfileMediaFiles.stream().filter(mediaFile -> mediaFile.getType().equals(insertType)).count();
        Integer size = MediaType.PICTURE.equals(insertType) ? imageSize : videoSize;
        if (cnt >= size) {
            throw new FullResourceException("최대 저장 개수를 초과했습니다.");
        }
    }

}
