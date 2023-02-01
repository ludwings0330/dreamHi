package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.FilmographyService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FilmographyController {

    private final FilmographyService filmographyService;

    @GetMapping("/api/filmographies")
    public ResponseEntity<Body> findFilmographies(@RequestBody FilmographyRequestDto requestDto) {
        final List<FilmographyResponseDto> responseDto = filmographyService.findFilmographies(requestDto);
        return Response.create(HttpStatus.OK, "ok", responseDto);
    }

    @PostMapping("/api/filmographies")
    public ResponseEntity<Body> addFilmography(@RequestBody FilmographyRequestDto requestDto,
                                               @AuthenticationPrincipal PrincipalDetails user) {
        // 필모그래피 수정 권한이 있는지 확인

        // 수정권한이 없으면 forbidden

        // 수정 권한이 있으면 수정
        filmographyService.addFilmography(requestDto);

        return Response.create(HttpStatus.CREATED, "필모그래피 추가 성공");
    }

    @DeleteMapping("/api/filmographies/{filmographyId}")
    public ResponseEntity<Body> deleteFilmography(@PathVariable Long filmographyId) {
        filmographyService.deleteFilmographyById(filmographyId);

        return Response.ok();
    }

    @PutMapping("/api/filmographies/{filmographyId}")
    public ResponseEntity<Body> updateFilmography(@PathVariable Long filmographyId,
                                                  @RequestBody FilmographyRequestDto filmographyRequestDto) {
        filmographyRequestDto.setFilmographyId(filmographyId);
        filmographyService.updateFilmography(filmographyRequestDto);
        return Response.ok();
    }

}
