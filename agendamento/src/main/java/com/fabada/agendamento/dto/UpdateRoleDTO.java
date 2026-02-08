package com.fabada.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleDTO(
        @NotBlank
        String username,
        @NotBlank
        String role
) {
}
