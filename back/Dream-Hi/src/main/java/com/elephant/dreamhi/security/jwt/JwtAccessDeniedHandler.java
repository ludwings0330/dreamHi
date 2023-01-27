package com.elephant.dreamhi.security.jwt;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 403 Forbidden Exception Handler Method
     *
     * @param request               : ServletRequest
     * @param response              : ServletResponse
     * @param accessDeniedException : Exception
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("Forbidden Error : {}", accessDeniedException.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), "권한, 인가 실패");
    }

}
