package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;

public interface UsernameExistValidated {
    void verify(String username) throws UsernameExistException;
}
