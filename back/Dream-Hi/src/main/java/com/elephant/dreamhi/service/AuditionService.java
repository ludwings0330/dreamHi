package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookPeriodSaveDto;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import com.elephant.dreamhi.model.dto.BookedVolunteerDto;
import com.elephant.dreamhi.model.dto.FileDto;
import com.elephant.dreamhi.model.entity.NoticeFile;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.time.LocalDate;
import java.util.List;

public interface AuditionService {

    BookResponseDto findBookOfVolunteer(Long processId, PrincipalDetails user) throws NotFoundException;

    BookPeriodDto findBookPeriod(Long processId) throws NotFoundException;

    List<BookResponseDto> findAllBookForVolunteer(Long processId, LocalDate date);

    List<BookProducerDto> findAllBookForProducer(Long producerId, LocalDate date);

    List<NoticeFile> findFileUrl(Long processId) throws NotFoundException;

    String findSessionId(Long processId) throws NotFoundException;

    List<BookedVolunteerDto> findBookedVolunteersOnToday(Long processId);

    void updateReserved(Long bookId, Long userId) throws NotFoundException, IllegalStateException;

    void createAuditionSchedule(Long processId, BookPeriodSaveDto bookPeriodSaveDto) throws NotFoundException;

    void saveAllNoticeFiles(Long processId, List<FileDto> fileDtos) throws NotFoundException;

}
