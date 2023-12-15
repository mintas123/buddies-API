package pl.mroz.buddiesapi.interfaces.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r
                                .requestMatchers("/").authenticated()
//                        .requestMatchers("/rentals").permitAll()
//                        .requestMatchers("/rentals/*").authenticated()
//                        .requestMatchers("/account/*").authenticated()
//                        .requestMatchers("/v3/api-docs/").hasAuthority("SCOPE_read:admin")
                        )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}