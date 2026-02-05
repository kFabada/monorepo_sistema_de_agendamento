package com.fabada.agendamento.validated;

import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.execption.UserRoleException;

public class UserRoleValidated {
    private final String role;

    public UserRoleValidated(String role){
        if(role.isBlank()) throw new UserRoleException("role invalid");
        this.role = role;
    }

    public void verify() {
        boolean valid = false;

        for (UserRole role : UserRole.values()){
            if(role.name().equals(this.role)){
                valid = true;
                break;
            }
        }

        if(!valid) throw new UserRoleException("role invalid");
    }
}
