package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.TokenDTO;

public interface JWTService {
    TokenDTO createToken();
}
