package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.FilmographyService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
        List<FilmographyResponseDto> responseDto = filmographyService.findFilmographies(requestDto);

        if (responseDto.isEmpty()) {
            return Response.create(HttpStatus.NO_CONTENT, "no content");
        }

        return Response.create(HttpStatus.OK, "필모그래피 목록 조회 성공", responseDto);
    }

    @PostMapping("/api/filmographies")
    @PreAuthorize("@checker.hasFilmographyAuthority(#user, #requestDto)")
    public ResponseEntity<Body> addFilmography(@RequestBody FilmographyRequestDto requestDto,
                                               @AuthenticationPrincipal PrincipalDetails user) throws AccessDeniedException, NotFoundException {
        filmographyService.addFilmography(requestDto);

        return Response.create(HttpStatus.CREATED, "필모그래피 생성 성공");
    }

    @DeleteMapping("/api/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasFilmographyAuthority(#user, #requestDto)")
    public ResponseEntity<Body> deleteFilmography(@RequestBody @Nullable FilmographyRequestDto requestDto,
                                                  @PathVariable Long filmographyId,
                                                  @AuthenticationPrincipal PrincipalDetails user) {
        filmographyService.deleteFilmography(filmographyId);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 삭제 성공");
    }

    @PutMapping("/api/filmographies/{filmographyId}")
    @PreAuthorize("@checker.hasFilmographyAuthority(#user, #requestDto)")
    public ResponseEntity<Body> updateFilmography(@RequestBody FilmographyRequestDto requestDto,
                                                  @PathVariable Long filmographyId,
                                                  @AuthenticationPrincipal PrincipalDetails user) {
        requestDto.setFilmographyId(filmographyId);

        filmographyService.updateFilmography(requestDto);

        return Response.create(HttpStatus.ACCEPTED, "필모그래피 정보 업데이트 성공");
    }

}
