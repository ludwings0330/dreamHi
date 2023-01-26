package com.elephant.dreamhi.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity // @Configuration + 추가 몇몇 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // Method 단위로 @PreAuthorize, @PostAuthorize 사용 가능하도록 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // web 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers(HttpMethod.OPTIONS, "/**")
           .antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CORS, CSRF 설정
                .cors()
                .and()
                .csrf().disable()

                // Session 설정 -> JWT 방식 로그인 구현 -> StateLess
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // request의 resource 접근 제한 설정
                .and()
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll();
    }

}