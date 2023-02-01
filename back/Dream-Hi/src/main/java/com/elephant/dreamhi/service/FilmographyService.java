package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.Filmography;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.FilmographyRepository;
import com.elephant.dreamhi.repository.FilmographyRepositoryCustom;
import com.elephant.dreamhi.repository.ProducerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilmographyService {

    private final ActorRepository actorRepository;
    private final ProducerRepository producerRepository;
    private final FilmographyRepository filmographyRepository;
    private final FilmographyRepositoryCustom filmographyRepositoryCustom;

    public List<FilmographyResponseDto> findFilmographies(FilmographyRequestDto requestDto) {
        return filmographyRepositoryCustom.findFilmographyByCondition(requestDto);
    }

    public void addFilmography(FilmographyRequestDto requestDto) {

        final Filmography filmography = Filmography.builder()
                                                   .title(requestDto.getTitle())
                                                   .originName(requestDto.getOriginName())
                                                   .savedName(requestDto.getSavedName())
                                                   .url(requestDto.getPhotoUrl())
                                                   .description(requestDto.getDescription()).build();

        if (requestDto.getActorId() != null) {
            final ActorProfile actorProfile = actorRepository.findById(requestDto.getActorId())
                                                             .orElseThrow(() -> new NotFoundException("존재하지 않는 배우프로필입니다."));
            filmography.setActorProfile(actorProfile);
        } else if (requestDto.getProducerId() != null) {
            final Producer producer = producerRepository.findById(requestDto.getProducerId())
                                                        .orElseThrow(() -> new NotFoundException("존재하지 않는 제작사입니다."));
            filmography.setProducer(producer);
        } else {
            throw new IllegalArgumentException("배우 프로필 혹은 제작사 아이디가 필요합니다.");
        }

        filmographyRepository.save(filmography);
    }

    public void deleteFilmographyById(Long filmographyId) {
        final Filmography filmography =
                filmographyRepository.findById(filmographyId).orElseThrow();
        // 삭제 권한 확인 로직 필요
        filmographyRepository.delete(filmography);
    }

    @Transactional
    public void updateFilmography(FilmographyRequestDto filmographyRequestDto) {
        // 업데이트할 권한이 있는지 확인
        final Filmography filmography =
                filmographyRepository.findById(filmographyRequestDto.getFilmographyId()).orElseThrow();
        filmography.updateInfo(filmographyRequestDto);
    }

}
