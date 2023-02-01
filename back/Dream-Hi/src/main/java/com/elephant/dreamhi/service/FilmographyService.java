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
@Transactional(readOnly = true)
public class FilmographyService {

    private final ActorRepository actorRepository;
    private final ProducerRepository producerRepository;
    private final FilmographyRepository filmographyRepository;
    private final FilmographyRepositoryCustom filmographyRepositoryCustom;

    public List<FilmographyResponseDto> findFilmographies(FilmographyRequestDto requestDto) {
        return filmographyRepositoryCustom.findFilmographyByCondition(requestDto);
    }

    @Transactional
    public void addFilmography(FilmographyRequestDto requestDto) {
        // actorProfile 혹은 Producer 찾아서 넣기.

        Filmography filmography = new Filmography();
        filmography.updateInfo(requestDto);

        if (requestDto.getActorId() != null) {
            ActorProfile actorProfile = actorRepository.findById(requestDto.getActorId())
                                                       .orElseThrow(() -> new NotFoundException("배우 프로필을 찾을 수 없습니다."));
            filmography.setActorProfile(actorProfile);
        } else if (requestDto.getProducerId() != null) {
            Producer producer = producerRepository.findById(requestDto.getProducerId())
                                                  .orElseThrow(() -> new NotFoundException("제작사를 찾을 수 없습니다."));
            filmography.setProducer(producer);
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
