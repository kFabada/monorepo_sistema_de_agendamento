package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;
import com.fabada.agendamento.execption.UsernameExistException;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidatedRegister {
    private final UsernameExistValidated usernameExistValidated;
    private final EmailExistValidated emailExistValidated;

    public UserValidatedRegister(UsernameExistValidated usernameExistValidated, EmailExistValidated emailExistValidated) {
        this.usernameExistValidated = usernameExistValidated;
        this.emailExistValidated = emailExistValidated;
    }

    public void userVerify(User user){
        if(usernameExistValidated.verify(user.getUsername())) throw new UsernameExistException("username already exist");
        if(emailExistValidated.verify(user.getEmail())) throw new EmailExistException("email already exist");
        UserRoleValidated roleValidated = new UserRoleValidated(user.getRole().name());
        roleValidated.verify();
    }
}
