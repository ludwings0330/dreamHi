package com.elephant.dreamhi.security.jwt;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 401 UnAuthorized Exception Handler Method
     *
     * @param request       : ServletRequest
     * @param response      : ServletResponse
     * @param authException : Exception
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("Authentication Exception : {} ", authException);
        log.error("UnAuthorized Error : {}", authException.getMessage());
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 실패");
    }

}
