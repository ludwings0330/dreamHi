package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.UserSimpleDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserSimpleDto findUserSimple(Long id) throws UsernameNotFoundException;

}
