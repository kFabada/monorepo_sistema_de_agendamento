package com.fabada.agendamento.utils;

import java.util.Map;

public interface JWTGeneratorInterface {
    String generator(String username, String scope);
    boolean validaJWT();
}
