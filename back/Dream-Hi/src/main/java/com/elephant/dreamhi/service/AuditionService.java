package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;

public interface AuditionService {

    String findFileUrl(Long processId);

    String findSessionId(Long processId);

    void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException;

}
