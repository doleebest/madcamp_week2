package madcamp_week2.repet.config.auth;

import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // spring security 활성화
public class SecurityConfig {
    @Autowired
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정 비활성화
                .csrf(csrf -> csrf.disable())

                // iframe 옵션 비활성화 (H2-console 사용 시 필요)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                // 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/list").permitAll() // /list 경로를 인증 없이 허용 (임시)
                        .requestMatchers("/posts/new", "/comments/save").hasRole(Role.USER.name())
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/login/*", "/logout/*", "/posts/**", "/comments/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 로그아웃 설정
                .logout(logout -> logout.logoutSuccessUrl("/list")) // 로그아웃 성공 시 메인 페이지 이동

                // OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)) // 사용자 서비스 설정
                        .successHandler(successHandler()) // 로그인 성공 시 리다이렉션 처리
                );



        return http.build();
    }

    // 로그인 성공 핸들러 추가
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            // 로그인 성공 후 "/post" 페이지로 리다이렉트
            response.sendRedirect("/list");
        };
    }
}
