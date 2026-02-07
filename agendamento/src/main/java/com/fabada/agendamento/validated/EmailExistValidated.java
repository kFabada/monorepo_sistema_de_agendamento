package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;
import com.fabada.agendamento.service.UserServiceInterface;
import org.springframework.stereotype.Component;


@Component
public class EmailExistValidated implements EmailExistValidatedInterface {
    private final UserServiceInterface userService;

    public EmailExistValidated(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public void verify(String email){
          if(userService.findByOptionalEmail(email).isPresent())
              throw new EmailExistException("email already exist");
    }
}
