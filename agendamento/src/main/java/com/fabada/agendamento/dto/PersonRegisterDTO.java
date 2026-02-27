package com.fabada.agendamento.dto;

import com.fabada.agendamento.model.Address;
import com.fabada.agendamento.model.Contact;
import com.fabada.agendamento.model.Person;
import com.fabada.agendamento.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public record PersonRegisterDTO(
        @NotNull(message = "não pode ser vazio o name")
        String name,
        @NotNull(message = "não pode ser vazio o cpf")
        String cpf,
        @NotNull(message = "não pode ser vazio o rg")
        String rg,
        @NotNull
        Date birthday,
        @NotNull
        Long user_id

) {
    public Person mapToPerson(User user){
        return new Person(name, cpf, rg, birthday, user);
    }
}
