package com.elephant.dreamhi.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final TokenProvider tokenProvider;

    /**
     * Security Filter Chain 중 들어갈 JWT 관련 Filter 이 필터는 AcccessToken,RefreshToken의 유효성 검증을 진행할 필터입니다.
     *
     * @param request     : ServletRequest
     * @param response    : ServletResponse
     * @param filterChain : Security Filter Chain
     * @throws ServletException, IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = tokenProvider.resolveToken(request);

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            log.info("JwtFilter with Token : {}", jwt);
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 {} 인증 정보를 저장했습니다. URI: {}", authentication.getName(), request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }

}
