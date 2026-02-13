package com.fabada.agendamento.utils;

import com.fabada.agendamento.dto.TokenDTO;

public interface JWTGenerator {
    TokenDTO generator(String username, String scope);
    boolean validaJWT();
}
