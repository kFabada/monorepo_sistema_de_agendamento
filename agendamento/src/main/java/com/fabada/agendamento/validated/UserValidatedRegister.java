package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidatedRegister {
    private final UsernameExistValidated usernameExistValidated;

    public UserValidatedRegister(UsernameExistValidated usernameExistValidated) {
        this.usernameExistValidated = usernameExistValidated;
    }

    public void userVerify(User user){
        if(usernameExistValidated.verify(user.getUsername())) throw new UsernameExistException("username already exist");
        UserRoleValidated roleValidated = new UserRoleValidated(user.getRole().name());
        roleValidated.verify();
    }
}
