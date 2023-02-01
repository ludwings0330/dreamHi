package com.elephant.dreamhi.service;

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

    public boolean hasActorProfileAuthority(Long userId, Long actorId) {
        return authRepository.findActorProfileByUserId(userId).orElse(-1L).equals(actorId);
    }

}
