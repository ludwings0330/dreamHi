package com.elephant.dreamhi.security;

import com.elephant.dreamhi.model.statics.ProducerRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("checker")
public class SecurityChecker {

    /**
     * producerId 에 대해 EDITOR 권한있는지 파악
     *
     * @param authentication : 현재 접근중인 주체 정보
     * @param producerId     : producer Id
     * @return 권한 있다면 true, 없다면 false
     */
    public boolean hasEditorAuthority(Authentication authentication, String producerId) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(ProducerRole.EDITOR.toString() + "_" + producerId))) {
            return true;
        }
        return false;
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
