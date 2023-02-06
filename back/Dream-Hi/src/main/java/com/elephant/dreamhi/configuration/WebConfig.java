package com.elephant.dreamhi.configuration;

import com.elephant.dreamhi.configuration.converter.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 메서드 허용
                .allowedMethods("*")
                // 요청 헤더 허용
                .allowedHeaders("*")
                // 오리진 허용
                .allowedOrigins("*")
                // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
                .allowCredentials(false);
    }

}
