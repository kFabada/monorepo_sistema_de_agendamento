package com.fabada.agendamento.utils;

import com.fabada.agendamento.dto.TokenDTO;

import java.util.Map;

public interface JWTGeneratorInterface {
    TokenDTO generator(String username, String scope);
    boolean validaJWT();
}
