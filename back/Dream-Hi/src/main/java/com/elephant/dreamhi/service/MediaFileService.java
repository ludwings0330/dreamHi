package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.MediaFileDto;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.repository.ActorProfileMediaFileRepository;
import com.elephant.dreamhi.repository.ActorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaFileService {

    private final ActorProfileMediaFileRepository actorProfileMediaFileRepository;

    private final ActorRepository actorRepository;

    public MediaFileDto findMediaFilesByActorProfileId(Long id) {
        List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(id);
        return new MediaFileDto(id, mediaFiles);
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
        actorRepository.checkValidateModify(actorProfileId, userId).orElseThrow(() -> new AccessDeniedException("권한이 없습니다."));
        actorProfileMediaFileRepository.deleteById(actorProfileMediaFileId);
        log.info("삭제 성공");
    }

}
