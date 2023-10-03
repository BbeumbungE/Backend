package com.siliconvalley.global.config.security.config;

import com.siliconvalley.global.config.security.jwt.JwtAccessDeniedHandler;
import com.siliconvalley.global.config.security.jwt.JwtAuthenticationEntryPoint;
import com.siliconvalley.global.config.security.jwt.JwtAuthorizationFilter;
import com.siliconvalley.global.config.security.jwt.JwtTokenProvider;
import com.siliconvalley.global.config.security.oauth.OAuth2AuthenticationSuccessHandler;
import com.siliconvalley.global.config.security.oauth.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured, preAuthorized, postAuthorized 어노테이션 활성화 -> 메소드 단위 권한설정 가능
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final OAuth2UserService oAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Value("${spring.security.logout-redirect-url}")
    private String redirectUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 인증 방식 설정
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable();

        // Cors 필터 추가, CSRF 비활성화
        httpSecurity.addFilterBefore(corsFilter, BasicAuthenticationFilter.class)
                .csrf().disable();

        // JWT Authorization 필터 추가
        httpSecurity
                .addFilter(jwtAuthorizationFilter())
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        // 인가 설정
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.OPTIONS).permitAll() // OPTIONS 메서드는 모두 허용
                        .antMatchers("/api/members/**").authenticated()
                        .antMatchers("/api/profiles/**").authenticated()
                        .anyRequest().permitAll()
                );

        // OAuth2 로그인 설정
        httpSecurity
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint()
                        .userService(oAuth2UserService)
                        .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler)
//                        .failureHandler(oAuth2AuthenticationFailureHandler)
                );

        // 로그아웃 설정
        httpSecurity.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(redirectUrl);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManagerBean(), jwtTokenProvider);
    }

}
