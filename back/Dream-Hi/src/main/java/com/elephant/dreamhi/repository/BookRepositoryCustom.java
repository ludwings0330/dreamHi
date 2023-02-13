package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepositoryCustom {


    Optional<BookPeriodDto> findBookPeriodByProcessId(Long processId);

    List<BookResponseDto> findAllForVolunteerByProcessIdAndDate(Long processId, LocalDate date);

    List<BookProducerDto> findAllForProducerByProducerIdAndDate(Long processId, LocalDate date);

}
