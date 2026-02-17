package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;
import com.fabada.agendamento.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class EmailExistValidatedImpl implements EmailExistValidated{
    private final UserService userService;

    public EmailExistValidatedImpl(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void verify(String email){
          if(userService.findByOptionalEmail(email).isPresent())
              throw new EmailExistException("email already exist");
    }
}
