package com.fabada.agendamento.utils;

import com.fabada.agendamento.execption.PasswordEncoderBlankException;

public interface PasswordEncoderInterface {
    String encoder(String password) throws  PasswordEncoderBlankException;
}
