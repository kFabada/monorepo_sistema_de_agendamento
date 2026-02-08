package com.fabada.agendamento.execption;

public class PasswordEncoderBlankException extends RuntimeException {
    public PasswordEncoderBlankException(String message) {
        super(message);
    }
}
