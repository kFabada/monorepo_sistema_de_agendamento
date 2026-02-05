package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsernameExistValidated {
    @Autowired
    private UserServiceInterface userService;

    public boolean verify(String username){
        try{
            userService.userbyUsername(username);
            return true;
        }catch (UsernameNotFoundException e){
            return false;
        }
    }
}
