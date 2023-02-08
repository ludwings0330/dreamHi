package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.model.dto.TokenDto;
import org.springframework.security.core.Authentication;

public interface TokenService {

    TokenDto generateToken(Authentication authentication);

    JwtResponse reissueAccessToken(String accessToken) throws IllegalArgumentException;

    void deleteToken(Long id);

}
