package com.fabada.agendamento.execption;

public class PersonExistException extends RuntimeException {
    public PersonExistException(String message) {
        super(message);
    }
}
