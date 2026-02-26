package com.fabada.agendamento.repository.spec;

import com.fabada.agendamento.dto.PersonUpdateDTO;
import com.fabada.agendamento.model.Person;
import org.springframework.data.jpa.domain.UpdateSpecification;

public class PersonSpec {

    public static UpdateSpecification<Person> updateSetParameters(PersonUpdateDTO personUpdateDTO) {
        return (root, update, criteriaBuilder) -> {

            if (!personUpdateDTO.name().isBlank()) {
                update.set("name", personUpdateDTO.name());
            }

            if (!personUpdateDTO.cpf().isBlank()) {
                update.set("cpf", personUpdateDTO.cpf());
            }

            if (!personUpdateDTO.rg().isBlank()) {
                update.set("rg", personUpdateDTO.rg());
            }

            if(personUpdateDTO.birthday() != null){
                update.set("birthday", personUpdateDTO.birthday());
            }

            return update.where(criteriaBuilder.equal(root.get("id"), personUpdateDTO.id())).getRestriction();
        };
    }
}


