package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.FileDto;
import com.elephant.dreamhi.model.dto.UserSimpleDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserSimpleDto findUserSimple(Long id) throws UsernameNotFoundException;

    void updateMainProfile(Long id, FileDto fileDto) throws UsernameNotFoundException;

}
