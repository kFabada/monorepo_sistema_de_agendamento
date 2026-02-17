package com.fabada.agendamento.validated;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.execption.UserRoleException;
import org.springframework.stereotype.Component;

@Component
public class UserRoleValidatedImpl implements UserRoleValidated {

    @Override
    public void verify(String role) {
       if(!UserRole.roleEquals(role)) throw new UserRoleException("role invalid");
    }
}
