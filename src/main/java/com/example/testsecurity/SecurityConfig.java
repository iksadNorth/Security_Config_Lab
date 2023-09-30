package com.example.testsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configurer를 여러 번 호출할 때,
 * 과연 맨 마지막 설정만 반영이 될 지
 * 아니면 연거푸 Configurer를 호출해도 매번 반영될 지
 * 실험하는 코드
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 만약 덮어씌우기라면 사라져야 할 설정 1.
        http.authorizeHttpRequests(b ->
                b.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/case1")).permitAll()
        );

        // 만약 덮어씌우기라면 사라져야 할 설정 2.
        http.authorizeHttpRequests(b ->
                b.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/case2")).permitAll()
        );

        // 어쨋든 살아남게 될 설정 3.
        http.authorizeHttpRequests(b ->
                b.anyRequest().authenticated()
        );

        return http.build();
    }
}
