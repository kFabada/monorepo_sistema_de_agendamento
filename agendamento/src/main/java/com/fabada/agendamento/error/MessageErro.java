package com.fabada.agendamento.error;

import lombok.Getter;

@Getter
public final class MessageErro {
    private final String message;

    public MessageErro(String message) {
        this.message = message;
    }
}
