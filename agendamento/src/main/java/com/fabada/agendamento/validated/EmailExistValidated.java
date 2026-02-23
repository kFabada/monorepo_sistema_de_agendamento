package com.fabada.agendamento.validated;

import com.fabada.agendamento.execption.EmailExistException;

public interface EmailExistValidated {
    void verify(String email) throws EmailExistException;
}
