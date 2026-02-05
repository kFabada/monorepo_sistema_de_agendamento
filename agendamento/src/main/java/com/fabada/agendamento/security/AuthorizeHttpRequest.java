package com.fabada.agendamento.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class AuthorizeHttpRequest {

    @Bean
    public SecurityFilterChain filterRequest(HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authRequest -> authRequest
                                .requestMatchers("/user/register", "/code/generation")
                                .permitAll()
                )
                .authorizeHttpRequests(authRequest -> authRequest
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
