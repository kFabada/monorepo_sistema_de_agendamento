package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;
import com.fabada.agendamento.execption.UsernameExistException;
import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidatedRegister implements UserValidatedRegisterInterface {
    private final UsernameExistValidatedInterface usernameExistValidated;
    private final EmailExistValidatedInterface emailExistValidated;
    private final UserRoleValidatedInferface userRoleValidated;

    public UserValidatedRegister(UsernameExistValidated usernameExistValidated, EmailExistValidated emailExistValidated, UserRoleValidatedInferface userRoleValidated) {
        this.usernameExistValidated = usernameExistValidated;
        this.emailExistValidated = emailExistValidated;
        this.userRoleValidated = userRoleValidated;
    }

    @Override
    public void verify(User user){
        usernameExistValidated.verify(user.getUsername());
        emailExistValidated.verify(user.getEmail());
        userRoleValidated.verify(user.getRole().name());
    }
}
