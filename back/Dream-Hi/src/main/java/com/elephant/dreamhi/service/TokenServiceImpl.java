package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.model.dto.TokenDto;
import com.elephant.dreamhi.model.entity.Token;
import com.elephant.dreamhi.repository.TokenRepository;
import com.elephant.dreamhi.repository.UserRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.security.jwt.TokenProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    /**
     * 로그인 시 토큰 생성 메소드
     *
     * @param authentication : 현재 접근중인 인증된 객체 Authentication
     * @return TokenDto
     */
    @Override
    @Transactional
    public TokenDto generateToken(Authentication authentication) {
        Long userId = ((PrincipalDetails) authentication.getPrincipal()).getId();
        String accessToken = tokenProvider.createAccessToken(authentication);

        Optional<Token> oldToken = tokenRepository.findByUserId(userId);
        TokenDto tokenDto = TokenDto.builder().id(userId).accessToken(accessToken).build();
        if (oldToken.isEmpty()) {
            String refreshToken = tokenProvider.createRefreshToken(authentication);
            createToken(tokenDto, refreshToken);
        } else {
            updateToken(tokenDto, oldToken.get());
        }

        return tokenDto;
    }

    /**
     * authentication을 이용해 새로운 토큰 발급, 토큰 저장
     *
     * @param authorization : Header에 담긴 Token 정보
     * @return JwtResponse 에 Access Token만 담아서 반환한다.
     * @throws IllegalArgumentException 현재 유저의 토큰 정보가 존재하지 않다면 발생
     */
    @Override
    @Transactional
    public JwtResponse reissueAccessToken(String authorization) throws IllegalArgumentException {
        String accessToken = authorization.substring(7);
        Token token = tokenRepository.findByUserIdAndAcessToken(accessToken)
                                     .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다. 보안 문제가 발생했습니다. 문의바랍니다."));

        String originRefreshToken = token.getRefreshToken();
        log.info("{}", originRefreshToken);
        tokenProvider.validateToken(originRefreshToken);
        Authentication authentication = tokenProvider.getAuthentication(originRefreshToken);
        String newAccessToken = tokenProvider.createAccessToken(authentication);

        // update access token
        token.changeAccessToken(newAccessToken);

        return JwtResponse.builder().id(((PrincipalDetails) authentication.getPrincipal()).getId()).accessToken(newAccessToken).build();
    }

    /**
     * 토큰 정보가 존재한다면 새로운 토큰으로 Update 메소드
     *
     * @param tokenDto : 새로 만든 토큰 DTO
     * @param oldToken : 기존에 저장된 토큰
     */
    private static void updateToken(TokenDto tokenDto, Token oldToken) {
        Token newToken = oldToken;
        newToken.updateAccessToken(tokenDto.getAccessToken());
    }

    /**
     * 로그아웃 시 DB에 Token data 삭제 메소드
     *
     * @param userId : 현재 접근중인 주체 userId
     * @throws IllegalArgumentException : 올바르지 않은 경우 발생합니다.
     */
    @Override
    @Transactional
    public void deleteToken(Long userId) throws IllegalArgumentException {
        if (tokenRepository.deleteByUserId(userId) != 1) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }

    /**
     * 새로운 토큰 Insert 메소드
     *
     * @param tokenDto : 새로 만든 토큰 DTO
     */
    private void createToken(TokenDto tokenDto, String refreshToken) {
        Token newToken = Token.builder().userId(tokenDto.getId()).accessToken(tokenDto.getAccessToken()).refreshToken(refreshToken).build();
        tokenRepository.save(newToken);
    }

}
