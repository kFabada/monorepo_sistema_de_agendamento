package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;
import com.fabada.agendamento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class UsernameExistValidatedImpl implements UsernameExistValidated {
    private final UserService userService;

    public UsernameExistValidatedImpl(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void verify(String username) throws UsernameExistException {
       if(userService.findByOptionalUsername(username).isPresent())
           throw new UsernameExistException("username already exist");
    }
}
