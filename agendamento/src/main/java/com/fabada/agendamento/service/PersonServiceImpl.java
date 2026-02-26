package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.PersonRegisterDTO;
import com.fabada.agendamento.dto.PersonUpdateDTO;
import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.Person;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.PersonRepository;
import com.fabada.agendamento.repository.spec.PersonSpec;
import com.fabada.agendamento.validated.PersonRegisterValidated;
import org.springframework.data.jpa.domain.UpdateSpecification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final PersonRegisterValidated personRegisterValidated;

    public PersonServiceImpl(PersonRepository personRepository, PersonRegisterValidated personRegisterValidated) {
        this.personRepository = personRepository;
        this.personRegisterValidated = personRegisterValidated;
    }

    @Override
    public void registerPerson(PersonRegisterDTO personRegisterDTODTO) {
        User user = personRegisterValidated.verify(personRegisterDTODTO);
        personRepository.save(personRegisterDTODTO.mapToPerson(user));
    }

    @Override
    public Long countByCpfOrRg(String cpf, String rg) {
        return personRepository.countByCpfOrRg(cpf, rg);
    }

    @Override
    public void updatePerson(PersonUpdateDTO personUpdateDTO) {
        UpdateSpecification<Person> specUpdate = PersonSpec.updateSetParameters(personUpdateDTO);
        personRepository.update(specUpdate);
    }
}
