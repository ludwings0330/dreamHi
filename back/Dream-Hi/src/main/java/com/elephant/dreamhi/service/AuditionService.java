package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriod;

public interface AuditionService {

    String findFileUrl(Long processId) throws NotFoundException;

    String findSessionId(Long processId) throws NotFoundException;

    void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException;

    BookPeriod findBookPeriod(Long processId);

}
