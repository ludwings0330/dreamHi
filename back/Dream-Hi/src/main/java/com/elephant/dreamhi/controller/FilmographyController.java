package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.elephant.dreamhi.service.FilmographyService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Body> addFilmography(@RequestBody FilmographyRequestDto requestDto) {
        filmographyService.addFilmography(requestDto);

        return Response.ok();
    }

    @DeleteMapping("/api/filmographies/{filmographyId}")
    public ResponseEntity<Body> deleteFilmography(@PathVariable Long filmographyId) {
        filmographyService.deleteFilmographyById(filmographyId);

        return Response.ok();
    }

}
