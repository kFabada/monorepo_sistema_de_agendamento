package com.fabada.agendamento.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderTypeImpl implements PasswordEncoderType {
   private final String ENCODER = "{bcrypt}";

    @Override
    public String getEncoder() {
        return ENCODER;
    }
}
