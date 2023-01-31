package com.elephant.dreamhi.service;

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

        // 둘다 없으면 안돼
        if (requestDto.getActorId() != null) {
            final ActorProfile actorProfile = actorRepository.findById(requestDto.getActorId()).orElseThrow();
            filmography.setActorProfile(actorProfile);
        } else if (requestDto.getProducerId() != null) {
            final Producer producer = producerRepository.findById(requestDto.getProducerId()).orElseThrow();
            filmography.setProducer(producer);
        } else {
            throw new RuntimeException();
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
