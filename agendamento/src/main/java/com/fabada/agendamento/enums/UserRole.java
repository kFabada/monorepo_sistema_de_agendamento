package com.fabada.agendamento.enums;

import com.fabada.agendamento.execption.UserRoleException;

public enum UserRole {
    ADM,
    CLIENT,
    EMPLOYEE;

    public static boolean roleEquals(String role){
        if(role.isBlank()) return false;

        for (UserRole roles : UserRole.values())
            if(roles.name().equals(role)) return true;

       return false;
    }
}
