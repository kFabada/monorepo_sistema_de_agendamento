package com.fabada.agendamento.dto;

import com.fabada.agendamento.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fabada.agendamento.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserRegisterDTO(
        @NotNull(message = "email não pode ser vazio")
        @Email(message = "precisa ser um email valido")
        String email,
        @NotNull(message = "username não pode ser vazio")
        String username,
        @NotNull(message = "password não pode ser vazio")
        String password
        ) {
        public User mapToUser(){
                return new User(username, email, password, UserRole.CLIENT);
        }
}
