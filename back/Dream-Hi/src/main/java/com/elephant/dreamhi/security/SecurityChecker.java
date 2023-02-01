package com.elephant.dreamhi.security;

import com.elephant.dreamhi.model.statics.ProducerRole;
import com.elephant.dreamhi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("checker")
@RequiredArgsConstructor
public class SecurityChecker {

    private final AuthService authService;

    public boolean hasEditorAuthority(PrincipalDetails user, Long producerId) {
        return authService.hasEditorAuthority(user.getId(), producerId);
    }

    public boolean hasActorProfileAuthority(PrincipalDetails user, Long actorProfileId) {
        return authService.hasActorProfileAuthority(user.getId(), actorProfileId);
    }

    /**
     * producerId 에 대해 MEMBER 권한있는지 파악
     *
     * @param authentication : 현재 접근중인 주체 정보
     * @param producerId     : producer Id
     * @return 권한 있다면 true, 없다면 false
     */
    public boolean hasMemberAuthority(Authentication authentication, String producerId) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(ProducerRole.MEMBER.toString() + "_" + producerId))) {
            return true;
        }
        return false;
    }

}
