package com.fabada.agendamento.error;


import com.fabada.agendamento.execption.JWTGeneratorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JWTError {

    @ExceptionHandler(JWTGeneratorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody MessageErro handleJWTGeneratorException(JWTGeneratorException e){
        return new MessageErro(e.getMessage());
    }
}
