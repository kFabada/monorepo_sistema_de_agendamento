package com.fabada.agendamento.dto;

import com.fabada.agendamento.model.Person;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PersonUpdateDTO(
        @NotBlank
        Long id,
        String name,
        String cpf,
        String rg,
        Date birthday
) {
    public Person mapToPerson(){
        return new Person(id, name, cpf, rg, birthday);
    }
}
