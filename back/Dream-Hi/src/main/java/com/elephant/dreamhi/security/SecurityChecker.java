package com.elephant.dreamhi.security;

import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.statics.FilmoType;
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

    public boolean hasFilmographyAuthority(PrincipalDetails user, FilmographyRequestDto requestDto) {
        Long actorId = requestDto.getActorId();
        Long producerId = requestDto.getProducerId();
        Long filmographyId = requestDto.getFilmographyId();

        if (actorId == null && producerId == null) {
            throw new IllegalArgumentException("배우 프로필 혹은 제작사 아이디가 필요합니다.");
        }

        if (actorId != null && producerId != null) {
            throw new IllegalArgumentException("배우 프로필 혹은 제작사 아이디 중 하나만 필요합니다.");
        }

        if (producerId != null) {
            // 필모 id 로 producerId 확인하고 여기서 요청한 producerId가 일치하는지 확인
            return hasEditorAuthority(user, producerId) && ((filmographyId == null)
                    || authService.hasFilmographyModifyAuthority(filmographyId, FilmoType.PRODUCER, producerId));
        }

        // 필모 id 로 actorId 확인하고 여기서 요청한 actorId가 일치하는지 확인
        return hasActorProfileAuthority(user, actorId) && ((filmographyId == null)
                || authService.hasFilmographyModifyAuthority(filmographyId, FilmoType.ACTOR, actorId));

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
