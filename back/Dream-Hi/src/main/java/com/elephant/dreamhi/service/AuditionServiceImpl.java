package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookRequestDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import com.elephant.dreamhi.model.entity.Book;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Session;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.StageName;
import com.elephant.dreamhi.repository.BookRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.SessionRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

    private final ProcessRepository processRepository;
    private final VolunteerRepository volunteerRepository;
    private final SessionRepository sessionRepository;
    private final BookRepository bookRepository;

    @Override
    public BookResponseDto findBookOfVolunteer(Long processId, PrincipalDetails user) throws NotFoundException {
        return bookRepository.findByUserIdAndProcessId(user.getId(), processId)
                             .map(BookResponseDto::toDto)
                             .orElseThrow(() -> new NotFoundException("아직 예약을 하지 않은 지원자입니다."));
    }

    @Override
    public BookPeriodDto findBookPeriod(Long processId) throws NotFoundException {
        return bookRepository.findBookPeriodByProcessId(processId)
                             .orElseThrow(() -> new NotFoundException("현재 절차에서 예약 가능한 기간을 찾을 수 없습니다. 제작사는 예약 기간을 등록해주세요."));
    }

    @Override
    public List<BookResponseDto> findAllBookForVolunteer(Long processId, LocalDate date) {
        return bookRepository.findAllForVolunteerByProcessIdAndDate(processId, date);
    }

    @Override
    public List<BookProducerDto> findAllBookForProducer(Long processId, LocalDate date) {
        return bookRepository.findAllForProducerByProducerIdAndDate(processId, date);
    }

    @Override
    public String findFileUrl(Long processId) throws NotFoundException {
        return sessionRepository.findByProcessId(processId)
                                .orElseThrow(() -> new NotFoundException("화상 오디션에 대한 세션을 찾을 수 없습니다."))
                                .getFileUrl();
    }

    @Override
    public String findSessionId(Long processId) throws NotFoundException {
        return sessionRepository.findByProcessId(processId)
                                .orElseThrow(() -> new NotFoundException("화상 오디션에 대한 세션을 찾을 수 없습니다."))
                                .getSessionId();
    }

    @Override
    @Transactional
    public void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException {
        Process process = findVideoProcessByProcessId(processId);
        String uniqueSessionId = UUID.randomUUID().toString();
        sessionRepository.save(new Session(process, uniqueSessionId, fileUrl));
    }

    @Override
    @Transactional
    public void saveBookOfVolunteer(Long processId, BookRequestDto bookRequestDto, PrincipalDetails user)
            throws NotFoundException, IllegalArgumentException {
        Process process = findVideoProcessByProcessId(processId);
        Volunteer volunteer = volunteerRepository.findByUserIdAndProcessId(user.getId(), processId).get(0);

        Book book = Book.builder()
                        .volunteer(volunteer)
                        .process(process)
                        .startTime(bookRequestDto.getStartDateTime())
                        .endTime(bookRequestDto.getEndDateTime())
                        .build();
        bookRepository.save(book);
    }

    private Process findVideoProcessByProcessId(Long processId) throws NotFoundException, IllegalArgumentException {
        Process process = processRepository.findById(processId)
                                           .orElseThrow(() -> new NotFoundException("현재 오디션의 절차를 찾을 수 없습니다."));

        if (process.getStage() != StageName.VIDEO) {
            throw new IllegalArgumentException("현재 오디션의 절차가 화상 오디션이 아닙니다.");
        }

        return process;
    }

}
