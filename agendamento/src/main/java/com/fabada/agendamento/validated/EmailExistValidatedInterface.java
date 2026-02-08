package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;

public interface EmailExistValidatedInterface {
    void verify(String email) throws EmailExistException;
}
