package com.fabada.agendamento.utils;

import com.fabada.agendamento.execption.PasswordEncoderBlankException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder implements PasswordEncoderInterface{

    @Override
    public String encoder(String password) throws PasswordEncoderBlankException{
        if(password.isBlank()) throw new PasswordEncoderBlankException("password blank");

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(16);
        return bcrypt.encode(password);
    }
}
