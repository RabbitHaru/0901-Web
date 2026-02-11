package me.shinsunyoung.springbootdeveloper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 주소
                .allowedOrigins("https://localhost:5173") // 리액트 주소
                // 실행 가능한 HTTP메서드 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*") // 모든 헤더를 허용
                .allowCredentials(true) // 쿠키/인증 세션 허용
                .maxAge(3600); // 요청처리시 최대 시간
    }
}
