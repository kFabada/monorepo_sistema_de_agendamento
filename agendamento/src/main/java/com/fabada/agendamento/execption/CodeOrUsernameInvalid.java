package com.fabada.agendamento.execption;

public class CodeOrUsernameInvalid extends RuntimeException {
    public CodeOrUsernameInvalid(String message) {
        super(message);
    }
}
