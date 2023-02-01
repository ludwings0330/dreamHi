package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.FilmographyService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmographyController {

    private final FilmographyService filmographyService;

    @GetMapping("/api/filmographies")
    public ResponseEntity<Body> findFilmographies(@ModelAttribute FilmographyRequestDto requestDto) {
        log.info("필모그래피 목록 조회 요청 : [{}]", requestDto);

        List<FilmographyResponseDto> responseDto = filmographyService.findFilmographies(requestDto);

        if (responseDto.isEmpty()) {
            return Response.create(HttpStatus.NO_CONTENT, "no content");
        }

        return Response.create(HttpStatus.OK, "필모그래피 목록 조회 성공", responseDto);
    }

    @PostMapping("/api/actors/{actorId}/filmographies")
    @PreAuthorize("@checker.hasActorProfileAuthority(#user, #actorId)")
    public ResponseEntity<Body> addActorFilmography(@RequestBody FilmographyRequestDto requestDto,
                                                    @PathVariable Long actorId,
                                                    @AuthenticationPrincipal PrincipalDetails user) {
        requestDto.setActorId(actorId);
        filmographyService.addFilmography(user.getId(), requestDto);

        return Response.create(HttpStatus.CREATED, "필모그래피 추가 성공");
    }

    @PostMapping("/api/producers/{producerId}/filmographies")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> addProducerFilmography(@RequestBody FilmographyRequestDto requestDto,
                                                       @PathVariable Long producerId,
                                                       @AuthenticationPrincipal PrincipalDetails user) {
        requestDto.setProducerId(producerId);
        filmographyService.addFilmography(user.getId(), requestDto);

        return Response.create(HttpStatus.CREATED, "필모그래피 추가 성공");
    }

    @DeleteMapping("/api/actors/{actorId}/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasActorProfileAuthority(#user, #actorId)")
    public ResponseEntity<Body> deleteActorFilmography(@PathVariable Long filmographyId,
                                                       @PathVariable Long actorId,
                                                       @AuthenticationPrincipal PrincipalDetails user) {
        log.info("필모그래피 삭제 요청 - actor id : {}, filmography id : {}", actorId, filmographyId);

        filmographyService.deleteFilmographyById(filmographyId);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 삭제 성공");
    }

    @DeleteMapping("/api/producers/{producerId}/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasActorProfileAuthority(#user, #producerId)")
    public ResponseEntity<Body> deleteProducerFilmography(@PathVariable Long filmographyId,
                                                          @PathVariable Long producerId,
                                                          @AuthenticationPrincipal PrincipalDetails user) {
//        프론트에는 이미 actorId 혹은 producerId를 가지고 있음.
        log.info("필모그래피 삭제 요청 - producer id : {}, filmography id : {}", producerId, filmographyId);
        filmographyService.deleteFilmographyById(filmographyId);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 삭제 성공");
    }

    @PutMapping("/api/actors/{actorId}/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasActorProfileAuthority(#user, #actorId)")
    public ResponseEntity<Body> updateActorFilmography(@PathVariable Long filmographyId,
                                                       @PathVariable Long actorId,
                                                       @RequestBody FilmographyRequestDto filmographyRequestDto,
                                                       @AuthenticationPrincipal PrincipalDetails user) {
        filmographyRequestDto.setFilmographyId(filmographyId);
        filmographyService.updateFilmography(filmographyRequestDto);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 정보 수정 성공");
    }

    @PutMapping("/api/producers/{producerId}/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> updateProducerFilmography(@PathVariable Long filmographyId,
                                                          @PathVariable Long producerId,
                                                          @RequestBody FilmographyRequestDto filmographyRequestDto,
                                                          @AuthenticationPrincipal PrincipalDetails user) {
        filmographyRequestDto.setFilmographyId(filmographyId);
        filmographyService.updateFilmography(filmographyRequestDto);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 정보 수정 성공");
    }

    @PostMapping("/api/filmographies")
    public ResponseEntity<Body> addFilmography(@RequestBody FilmographyRequestDto requestDto,
                                               @AuthenticationPrincipal PrincipalDetails user) throws AccessDeniedException, NotFoundException {
        filmographyService.addFilmography(user.getId(), requestDto);

        return Response.create(HttpStatus.CREATED, "필모그래피 생성 성공");
    }

    @DeleteMapping("/api/filmographies/{filmographyId}")
    public ResponseEntity<Body> deleteFilmography(@RequestBody FilmographyRequestDto requestDto,
                                                  @PathVariable @NotNull Long filmographyId,
                                                  @AuthenticationPrincipal PrincipalDetails user) {
        requestDto.setFilmographyId(filmographyId);
        filmographyService.deleteFilmography(user.getId(), requestDto);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 삭제 성공");
    }

    public ResponseEntity<Body> updateFilmography() {
        return Response.ok();
    }

}
