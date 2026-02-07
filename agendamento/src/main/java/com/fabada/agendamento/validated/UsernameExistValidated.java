package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;
import com.fabada.agendamento.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UsernameExistValidated implements UsernameExistValidatedInterface {
    @Autowired
    private UserServiceInterface userService;

    @Override
    public void verify(String username) throws UsernameExistException {
       if(userService.findByOptionalUsername(username).isPresent())
           throw new UsernameExistException("username already exist");
    }
}
