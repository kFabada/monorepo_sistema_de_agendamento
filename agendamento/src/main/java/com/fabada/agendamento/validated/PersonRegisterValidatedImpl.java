package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.PersonRegisterDTO;
import com.fabada.agendamento.execption.PersonExistException;
import com.fabada.agendamento.execption.UserNotFoundException;
import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.PersonRepository;
import com.fabada.agendamento.repository.UserRepository;
import com.fabada.agendamento.service.PersonService;
import com.fabada.agendamento.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonRegisterValidatedImpl implements PersonRegisterValidated{

    private final PersonService personService;
    private final UserService userService;

    public PersonRegisterValidatedImpl(@Lazy  PersonService personService, @Lazy UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    @Override
    public User verify(PersonRegisterDTO personRegisterDTO) {
        if(personService.countByCpfOrRg(personRegisterDTO.cpf(), personRegisterDTO.rg()) > 0){
            throw new PersonExistException("cpf or rg already exist");
        }

        Optional<User> userOptional = userService.findbyId(personRegisterDTO.user_id());
        if(userOptional.isEmpty()) throw new UserNotFoundException("user don`t register");

        return userOptional.get();
    }
}
