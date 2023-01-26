package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.TokenDto;
import java.sql.SQLException;
import org.springframework.security.core.Authentication;

public interface TokenService {

    TokenDto generateToken(Authentication authentication) throws SQLException;

}
