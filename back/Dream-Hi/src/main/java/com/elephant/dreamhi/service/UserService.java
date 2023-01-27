package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.UserDetailDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface UserService {

    UserDetailDto findUserDetail(Long id) throws UsernameNotFoundException;

}
