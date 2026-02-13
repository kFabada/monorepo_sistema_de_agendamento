package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;
import com.fabada.agendamento.service.UserService;
import org.springframework.stereotype.Component;


@Component
public class EmailExistValidated implements EmailExistValidatedInterface {
    private final UserService userService;

    public EmailExistValidated(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void verify(String email){
          if(userService.findByOptionalEmail(email).isPresent())
              throw new EmailExistException("email already exist");
    }
}
