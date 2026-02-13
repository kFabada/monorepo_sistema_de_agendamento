package com.fabada.agendamento.dto;

import com.fabada.agendamento.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fabada.agendamento.model.User;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRegisterDTO(
        @NotBlank
        @Email(message = "email don't valid")
        String email,
        @NotBlank
        String username,
        @NotBlank
        String password
        ) {
        public User mapToUser(){
                return new User(username, email, password, UserRole.CLIENT);
        }
}
