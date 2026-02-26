package com.fabada.agendamento.dto;

import com.fabada.agendamento.model.Address;
import com.fabada.agendamento.model.Contact;
import com.fabada.agendamento.model.Person;
import com.fabada.agendamento.model.User;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public record PersonRegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        Date birthday,
        @NotBlank
        Long user_id

) {
    public Person mapToPerson(User user){
        return new Person(name, cpf, rg, birthday, user);
    }
}
