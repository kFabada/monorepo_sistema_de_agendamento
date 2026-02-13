package com.fabada.agendamento.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantyAuthority implements GrantedAuthority {
    private final String role;

    public CustomGrantyAuthority(String role) {
        this.role = role;
    }

    @Override
    public @Nullable String getAuthority() {
        return role;
    }
}
