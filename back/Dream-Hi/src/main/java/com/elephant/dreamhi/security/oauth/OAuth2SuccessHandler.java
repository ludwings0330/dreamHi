package com.elephant.dreamhi.security.oauth;

import com.elephant.dreamhi.model.dto.TokenDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.TokenService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${app.oauth2.authorizedRedirectUri}")
    private String redirectUrl;

    private final TokenService tokenService;

    /**
     * 소셜 로그인 성공적으로 수행된 후 수행되는 SuccessHandler 메소드
     *
     * @param request
     * @param response
     * @param authentication : 로그인 성공 후 SecurityContext에 저장된 Authentication 객체
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        log.info("Authentication : {}, Principal : {}", authentication, authentication.getPrincipal());
        log.info("redirectUri : {}", redirectUrl);

        TokenDto tokenDto = null;
        tokenDto = tokenService.generateToken(authentication);

        log.info("{} 번 유저 token 발행 => Access Token = {} \t Refresh Token = {}", tokenDto.getId(), tokenDto.getAccessToken(),
                 tokenDto.getRefreshToken());

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String targetUrl = getTargetUrl(principal, tokenDto);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    /**
     * Front Redirect 할 URL 생성 메소드
     *
     * @param principal : Authentication 에서 접근 주체인 Principal 객체 정보
     * @param tokenDto  : 로그인 성공 시 토큰 생성 -> tokenDto
     * @return redirectURL
     */
    private String getTargetUrl(PrincipalDetails principal, TokenDto tokenDto) {
        return UriComponentsBuilder
                .fromUriString(redirectUrl)
                .queryParam("isNew", principal.getIsNew())
                .queryParam("accessToken", tokenDto.getAccessToken())
                .queryParam("refreshToken", tokenDto.getRefreshToken())
                .build().toString();
    }

}
