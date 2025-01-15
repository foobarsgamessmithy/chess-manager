package eu.foobarssgamesmithy.chessmanager.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] OPEN_REQUEST_PATH = {
            "/api/**",
            "/actuator/**",
            "/h2/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // For now, we disable csrf because only clients will send requests
                // https://docs.spring.io/spring-security/reference/features/exploits/csrf.html
                .csrf(AbstractHttpConfigurer::disable)
                // FIXME: disable for h2 console. should be moved to development profile later
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(OPEN_REQUEST_PATH)
                            .permitAll()
                    .anyRequest().authenticated()
                );
        return http.build();
    }

}
