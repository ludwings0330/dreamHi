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
import org.springframework.security.access.AccessDeniedException;
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

    private final AuthService authService;

    public List<FilmographyResponseDto> findFilmographies(FilmographyRequestDto requestDto) {
        return filmographyRepositoryCustom.findFilmographyByCondition(requestDto);
    }

    @Transactional
    public Long addFilmography(FilmographyRequestDto requestDto) throws AccessDeniedException, NotFoundException {
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

        return filmography.getId();
    }

    @Transactional
    public void deleteFilmographyById(Long filmographyId) {
        final Filmography filmography =
                filmographyRepository.findById(filmographyId)
                                     .orElseThrow(() -> new NotFoundException("존재하지 않는 필모그래피입니다."));

        filmographyRepository.delete(filmography);
    }

    @Transactional
    public void updateFilmography(FilmographyRequestDto filmographyRequestDto) {
        final Filmography filmography =
                filmographyRepository.findById(filmographyRequestDto.getFilmographyId())
                                     .orElseThrow(() -> new NotFoundException("존재하지 않는 필모그래피입니다."));

        filmography.updateInfo(filmographyRequestDto);
    }

    @Transactional
    public void deleteFilmography(Long filmographyId) {
        filmographyRepository.deleteById(filmographyId);
    }

}
