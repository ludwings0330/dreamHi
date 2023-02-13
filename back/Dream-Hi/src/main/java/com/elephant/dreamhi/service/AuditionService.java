package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;

public interface AuditionService {

    String findFileUrl(Long processId) throws NotFoundException;

    String findSessionId(Long processId) throws NotFoundException;

    void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException;

}
