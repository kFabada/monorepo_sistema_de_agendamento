package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatedRegister {
    private UsernameExistValidated usernameExistValidated;

    public UserValidatedRegister(UsernameExistValidated usernameExistValidated) {
        this.usernameExistValidated = usernameExistValidated;
    }

    public void userVerify(String username, String role){
        if(usernameExistValidated.verify(username)) throw new UsernameExistException("username already exist");
        UserRoleValidated roleValidated = new UserRoleValidated(role);
        roleValidated.verify();
    }
}
