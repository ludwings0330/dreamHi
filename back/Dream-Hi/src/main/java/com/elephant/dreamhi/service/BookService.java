package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.AuditionCreateRequestDto;
import com.elephant.dreamhi.model.entity.Book;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.repository.BookRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final VolunteerRepository volunteerRepository;

    @Value("${app.schedule-period}")
    private Long schedulePeriod;
    private final BookRepository bookRepository;
    private final ProcessRepository processRepository;

    /**
     * 오디션 일정 생성 메소드
     *
     * @param processId : 현재 채용 절차 id
     * @param auditionCreateRequestDto : request DTO
     * */
    @Transactional
    public void createAuditionSchedule(Long processId, AuditionCreateRequestDto auditionCreateRequestDto) {
        Process process = processRepository.getReferenceById(processId);
        List<Book> books = Book.toEntityList(process, auditionCreateRequestDto, schedulePeriod);
        Long totalVoluteerCount = volunteerRepository.countByCurrentProcessId(processId);
        if(books.size() < totalVoluteerCount) throw new IllegalArgumentException("오디션 가능 일자가 너무 적습니다.");
        bookRepository.saveAll(books);
    }

}
