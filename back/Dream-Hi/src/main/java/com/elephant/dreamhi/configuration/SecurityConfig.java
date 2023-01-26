package com.elephant.dreamhi.configuration;

import com.elephant.dreamhi.security.jwt.JwtAccessDeniedHandler;
import com.elephant.dreamhi.security.jwt.JwtAuthenticationEntryPoint;
import com.elephant.dreamhi.security.jwt.JwtFilter;
import com.elephant.dreamhi.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final TokenProvider tokenProvider;

    private final PrincipalOAuth2UserService principalOauth2UserService;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    // PasswordEncoder 로 인한 순환 참조 발생 -> AppConfig로 따리 관리
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                         .antMatchers(HttpMethod.OPTIONS, "/**")
                         .antMatchers("/favicon.ico");
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors()
                   .and()
                   .csrf()
                   .disable()
                   .exceptionHandling()
                   .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                   .accessDeniedHandler(jwtAccessDeniedHandler) // 생략해도 AdviceController에서 잡아주기 때문에 괜찮습니다.
                   .and()
                   .sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   .and()
                   .authorizeRequests()
                   .antMatchers("/api/**").authenticated()
                   .antMatchers("/auth/**", "/oauth2/**", "/login/**").permitAll()
                   .anyRequest().authenticated()
                   .and()
                   .oauth2Login()
                   .userInfoEndpoint()
                   .userService(principalOauth2UserService)
                   .and()
                   .successHandler(oAuth2SuccessHandler);

        return http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class).build();
    }
}