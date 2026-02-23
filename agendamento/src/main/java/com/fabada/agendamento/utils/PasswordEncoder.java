package com.fabada.agendamento.utils;

import com.fabada.agendamento.execption.PasswordEncoderBlankException;

public interface PasswordEncoder {
    String encoder(String password) throws  PasswordEncoderBlankException;
}
