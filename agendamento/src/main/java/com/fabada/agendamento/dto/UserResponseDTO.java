package com.fabada.agendamento.dto;

import com.fabada.agendamento.enums.UserRole;

public record UserResponseDTO(Long id, String username, UserRole role) {
}
