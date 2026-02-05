package com.fabada.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCodeDTO(
        @NotBlank
        String username) {
}
