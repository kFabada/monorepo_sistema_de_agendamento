package com.fabada.agendamento.dto;

import com.fabada.agendamento.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import com.fabada.agendamento.model.User;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRegisterDTOValidated(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String role
        ) {

        public User mapToUser(){
                return new User(username, password, UserRole.valueOf(role));
        }
}
