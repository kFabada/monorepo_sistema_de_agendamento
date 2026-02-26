package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.PersonRegisterDTO;
import com.fabada.agendamento.dto.PersonUpdateDTO;
import com.fabada.agendamento.model.Person;

public interface PersonService {
    void registerPerson(PersonRegisterDTO personRegisterDTO);
    Long countByCpfOrRg(String cpf, String rg);
    void updatePerson(PersonUpdateDTO personUpdateDTO);
}
