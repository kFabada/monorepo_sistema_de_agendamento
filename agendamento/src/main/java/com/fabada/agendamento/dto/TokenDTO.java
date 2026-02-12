package com.fabada.agendamento.dto;

import java.time.Instant;

public record TokenDTO(String token, String scope, Instant iat, Instant exp) {
}
