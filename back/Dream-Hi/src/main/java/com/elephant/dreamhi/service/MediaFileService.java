package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.MediaFileDto;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.repository.ActorProfileMediaFileRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaFileService {

    private final ActorProfileMediaFileRepository actorProfileMediaFileRepository;

    public MediaFileDto findMediaFilesByActorProfileId(Long id) {
        List<ActorProfileMediaFile> mediaFiles = actorProfileMediaFileRepository.findAllByActorProfile_Id(id);
        return new MediaFileDto(id, mediaFiles);
    }

}
