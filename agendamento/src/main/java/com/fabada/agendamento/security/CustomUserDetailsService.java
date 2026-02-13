package com.fabada.agendamento.security;

import com.fabada.agendamento.model.User;
import com.fabada.agendamento.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
@EnableWebSecurity
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoderType passwordEncoderType;

    public CustomUserDetailsService(UserService userService, PasswordEncoderType passwordEncoderType) {
        this.userService = userService;
        this.passwordEncoderType = passwordEncoderType;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByOptionalUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return new CustomUserDetails(
                List.of(user.getRole().name()),
                passwordEncoderType.getEncoder() + user.getPassword(),
                user.getUsername()
        );
    }
}
