package com.elephant.dreamhi.security.oauth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${app.oauth2.authorizedRedirectUri}")
    private String redirectUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        log.info("[OAuth Login Failure] : {}", exception.getLocalizedMessage());

        String targetUrl = getTargetUrl(exception);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    /**
     * Front Redirect 할 URL 생성 메소드
     *
     * @param exception
     * @return redirectURL
     */
    private String getTargetUrl(AuthenticationException exception) {
        String targetUrl = UriComponentsBuilder
                .fromUriString(redirectUrl)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toString();
        return targetUrl;
    }

}
