package com.elephant.dreamhi.service;

public interface SessionService {

    void saveSession(Long announcementId, String sessionId);

    String findLatestSessionIdByAnnouncementId(Long announcementId);

}
