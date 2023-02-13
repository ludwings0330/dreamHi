package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriod;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import java.time.LocalDate;
import java.util.List;

public interface AuditionService {

    BookPeriod findBookPeriod(Long processId);

    List<BookResponseDto> findAllBookForVolunteer(Long processId, LocalDate date);

    List<BookProducerDto> findAllBookForProducer(Long producerId, LocalDate date);

    String findFileUrl(Long processId) throws NotFoundException;

    String findSessionId(Long processId) throws NotFoundException;

    void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException;

}
