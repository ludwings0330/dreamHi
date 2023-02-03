package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.statics.FilmoType;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.elephant.dreamhi.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public boolean hasEditorAuthority(Long userId, Long producerId) {
        ProducerRole role = authRepository.findRoleByUser_IdAndProducer_Id(userId, producerId)
                                          .orElseGet(() -> ProducerRole.MEMBER);
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

}
