package com.fabada.agendamento.validated;

import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidatedRegisterImpl implements UserValidatedRegister {
    private final UsernameExistValidated usernameExistValidated;
    private final EmailExistValidated emailExistValidated;
    private final UserRoleValidated userRoleValidated;

    public UserValidatedRegisterImpl(UsernameExistValidated usernameExistValidated, EmailExistValidated emailExistValidated, UserRoleValidated userRoleValidated) {
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
