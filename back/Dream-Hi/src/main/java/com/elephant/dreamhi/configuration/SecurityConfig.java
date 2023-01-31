package com.elephant.dreamhi.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    private final TokenProvider tokenProvider;
//
//    private final PrincipalOAuth2UserService principalOauth2UserService;
//
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//
//    // PasswordEncoder 로 인한 순환 참조 발생 -> AppConfig로 따리 관리
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring()
//                         .antMatchers(HttpMethod.OPTIONS, "/**")
//                         .antMatchers("/favicon.ico");
//    }
//
//    @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler) // 생략해도 AdviceController에서 잡아주기 때문에 괜찮습니다.
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/**").authenticated()
//                .antMatchers("/auth/**", "/oauth2/**", "/login/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService)
//                .and()
//                .successHandler(oAuth2SuccessHandler);
//
//        return http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class).build();
//    }
//}

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