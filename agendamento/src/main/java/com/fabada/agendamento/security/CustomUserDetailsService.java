package com.fabada.agendamento.security;

import com.fabada.agendamento.model.User;
import com.fabada.agendamento.service.UserServiceInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceInterface userService;
    private final PasswordEncoderTypeInterface passwordEncoderType;

    public CustomUserDetailsService(UserServiceInterface userService, PasswordEncoderTypeInterface passwordEncoderType) {
        this.userService = userService;
        this.passwordEncoderType = passwordEncoderType;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByOptionalUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("username not found");

        return new CustomUserDetails(
                List.of(user.get().getRole().name()),
                passwordEncoderType.getEncoder() + user.get().getPassword(),
                user.get().getUsername()
        );
    }
}
