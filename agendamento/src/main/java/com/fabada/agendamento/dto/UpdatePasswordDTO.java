package com.fabada.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        int code) {
}
