package com.fabada.agendamento.dto;

import com.fabada.agendamento.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponsePageDTO(
        Long id,
        String username,
        String email,
        UserRole role,
        LocalDateTime register,
        LocalDateTime lastUpdate
) {
}
