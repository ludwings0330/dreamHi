package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookRequestDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import com.elephant.dreamhi.model.dto.BookedVolunteerDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.time.LocalDate;
import java.util.List;

public interface AuditionService {

    BookResponseDto findBookOfVolunteer(Long processId, PrincipalDetails user) throws NotFoundException;

    BookPeriodDto findBookPeriod(Long processId) throws NotFoundException;

    List<BookResponseDto> findAllBookForVolunteer(Long processId, LocalDate date);

    List<BookProducerDto> findAllBookForProducer(Long producerId, LocalDate date);

    String findFileUrl(Long processId) throws NotFoundException;

    String findSessionId(Long processId) throws NotFoundException;

    void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException;

    void saveBookOfVolunteer(Long processId, BookRequestDto bookRequestDto, PrincipalDetails user) throws NotFoundException, IllegalArgumentException;

    List<BookedVolunteerDto> findBookedVolunteersOnToday(Long processId);

}
