package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.UsernameExistException;

public interface UsernameExistValidatedInterface {
    void verify(String username) throws UsernameExistException;
}
