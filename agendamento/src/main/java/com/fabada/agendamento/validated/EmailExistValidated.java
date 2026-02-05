package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailNotFoundException;
import com.fabada.agendamento.service.UserServiceInterface;

public class EmailExistValidated {
    private final UserServiceInterface userService;

    public EmailExistValidated(UserServiceInterface userService) {
        this.userService = userService;
    }

    public boolean verify(String email){
        try{
            userService.userByEmail(email);
            return true;
        }catch (EmailNotFoundException e){
            return false;
        }
    }
}
