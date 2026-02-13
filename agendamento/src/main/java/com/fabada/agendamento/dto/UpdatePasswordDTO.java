package com.fabada.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDTO(
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String code) {
}
