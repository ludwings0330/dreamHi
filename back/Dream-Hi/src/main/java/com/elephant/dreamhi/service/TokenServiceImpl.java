package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.TokenDto;
import com.elephant.dreamhi.model.entity.Token;
import com.elephant.dreamhi.repository.TokenRepository;
import com.elephant.dreamhi.repository.UserRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.security.jwt.TokenProvider;
import java.sql.SQLException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    /**
     * 로그인 시 토큰 생성 메소드
     *
     * @param authentication : 현재 접근중인 인증된 객체 Authentication
     * @return TokenDto
     * @throws SQLException
     */
    @Override
    @Transactional
    public TokenDto generateToken(Authentication authentication) throws SQLException {
        TokenDto tokenDto = tokenProvider.createNewToken(authentication);
        Long userId = ((PrincipalDetails) authentication.getPrincipal()).getId();

        Optional<Token> oldToken = tokenRepository.findByUserId(userId);
        if (oldToken.isEmpty()) {
            createToken(tokenDto);
        } else {
            updateToken(tokenDto, oldToken);
        }

        return tokenDto;
    }

    /**
     * 토큰 정보가 존재한다면 새로운 토큰으로 Update 메소드
     *
     * @param tokenDto : 새로 만든 토큰 DTO
     * @param oldToken : 기존에 저장된 토큰
     */
    private static void updateToken(TokenDto tokenDto, Optional<Token> oldToken) {
        Token newToken = oldToken.get();
        newToken.regenerateToken(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
    }

    /**
     * 새로운 토큰 Insert 메소드
     *
     * @param tokenDto : 새로 만든 토큰 DTO
     */
    private void createToken(TokenDto tokenDto) {
        Token newToken = Token.builder()
                              .userId(tokenDto.getId())
                              .accessToken(tokenDto.getAccessToken())
                              .refreshToken(tokenDto.getRefreshToken())
                              .build();
        tokenRepository.save(newToken);
    }

}
