package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import java.util.List;

public interface FilmographyRepositoryCustom {

    List<FilmographyResponseDto> findFilmographyByCondition(FilmographyRequestDto condition);

}
