package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.entity.Book;
import com.elephant.dreamhi.model.statics.FilmoType;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.elephant.dreamhi.repository.AuthRepository;
import com.elephant.dreamhi.repository.BookRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BookRepository bookRepository;
    private final VolunteerRepository volunteerRepository;

    public boolean hasEditorAuthority(Long userId, Long producerId) {
        ProducerRole role = authRepository.findRoleByUser_IdAndProducer_Id(userId, producerId)
                                          .orElse(ProducerRole.MEMBER);
        return role.equals(ProducerRole.EDITOR);
    }

    public boolean isAnonymous(Long userId) {
        return userId == 0;
    }

    public boolean hasActorProfileAuthority(Long userId, Long actorId) {
        return authRepository.findActorProfileByUserId(userId).orElse(-1L).equals(actorId);
    }

    public boolean hasFilmographyModifyAuthority(Long filmographyId, FilmoType type, Long id) {
        switch (type) {
            case ACTOR:
                return authRepository.findActorIdByFilmographyId(filmographyId).orElse(-1L).equals(id);
            case PRODUCER:
                return authRepository.findProducerIdByFilmographyId(filmographyId).orElse(-1L).equals(id);
            default:
                return false;
        }
    }

    public boolean hasAnnouncementAuthority(PrincipalDetails user, Long announcementId) {
        Long producerId = authRepository.findProducerIdByAnnouncementId(announcementId).orElse(-1L);

        return hasEditorAuthority(user.getId(), producerId);
    }

    public boolean hasAnnouncementAuthority(PrincipalDetails user, Long producerId, Long announcementId) {
        return authRepository.findRoleByUser_IdAndProducer_IdAndAnnouncement_Id(user.getId(), producerId, announcementId) == ProducerRole.EDITOR;
    }

    public boolean isPassedVolunteer(PrincipalDetails user, Long processId) {
        return volunteerRepository.findByUserIdAndProcessId(user.getId(), processId)
                                  .size() > 0;
    }

    public boolean hasBookAuthority(PrincipalDetails user, Long processId, LocalDateTime now) throws AccessDeniedException {
        Book book = bookRepository.findByUserIdAndProcessId(user.getId(), processId)
                                  .orElseThrow(() -> new AccessDeniedException("화상 오디션 예약 목록에서 찾을 수 없는 회원입니다."));

        if (now.isBefore(book.getStartTime()) || now.isAfter(book.getStartTime().plusMinutes(10L))) {
            throw new AccessDeniedException("현재 시간에는 화상 오디션에 접속할 수 없습니다. 오디션 시작 일시에 맞추어 입장해주세요.");
        }

        return true;
    }

}
