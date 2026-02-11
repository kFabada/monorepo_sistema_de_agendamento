package com.fabada.agendamento.service;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.utils.JWTGeneratorInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JWTServices implements JWTServiceInterface{
    private final JWTGeneratorInterface jwtGenerator;

    public JWTServices(JWTGeneratorInterface jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public String createToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<String> reduce = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .reduce((accumulator, element) -> {
                    if(UserRole.roleEquals(element)) return accumulator + " " + element;
                    return accumulator;
                });

        String role = reduce.orElse("");
        return jwtGenerator.generator(username, role);
    }
}
