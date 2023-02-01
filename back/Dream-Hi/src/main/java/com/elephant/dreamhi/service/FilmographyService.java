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
    public void addFilmography(Long userId, FilmographyRequestDto requestDto) throws AccessDeniedException, NotFoundException {
        // actorProfile 혹은 Producer 찾아서 넣기.

        Filmography filmography = new Filmography();
        filmography.updateInfo(requestDto);

        if (requestDto.getActorId() != null) {
            Long actorId = requestDto.getActorId();

            if (!authService.hasActorProfileAuthority(userId, actorId)) {
                throw new AccessDeniedException("접근 거부");
            }

            ActorProfile actorProfile = actorRepository.findById(actorId)
                                                       .orElseThrow(() -> new NotFoundException("배우 프로필을 찾을 수 없습니다."));
            filmography.setActorProfile(actorProfile);
        } else if (requestDto.getProducerId() != null) {
            Long producerId = requestDto.getProducerId();

            if (!authService.hasEditorAuthority(userId, producerId)) {
                throw new AccessDeniedException("접근 거부");
            }

            Producer producer = producerRepository.findById(requestDto.getProducerId())
                                                  .orElseThrow(() -> new NotFoundException("제작사를 찾을 수 없습니다."));
            filmography.setProducer(producer);
        } else {
            throw new IllegalArgumentException("배우 프로필 혹은 제작사 아이디가 필요합니다.");
        }

        filmographyRepository.save(filmography);
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
    public void deleteFilmography(Long userId, FilmographyRequestDto requestDto) throws AccessDeniedException, IllegalArgumentException {
        final Long actorId = requestDto.getActorId();
        final Long producerId = requestDto.getProducerId();

        if (actorId != null) {
            final Long findId = filmographyRepository.findActorIdById(requestDto.getFilmographyId())
                                                     .orElse(-1L);

            if (!(findId.equals(actorId) && authService.hasActorProfileAuthority(userId, actorId))) {
                throw new AccessDeniedException("접근이 거부되었습니다.");
            }
        } else if (producerId != null) {
            final Long findId = filmographyRepository.findProducerIdById(requestDto.getFilmographyId())
                                                     .orElse(-1L);

            if (!(findId.equals(producerId) && authService.hasEditorAuthority(userId, producerId))) {
                throw new AccessDeniedException("접근이 거부되었습니다.");
            }
        } else {
            throw new IllegalArgumentException("배우 프로필 혹은 제작사 아이디가 필요합니다.");
        }

        filmographyRepository.deleteById(requestDto.getFilmographyId());
    }

}
