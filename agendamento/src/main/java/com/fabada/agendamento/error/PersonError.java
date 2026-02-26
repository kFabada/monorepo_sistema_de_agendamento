package com.fabada.agendamento.error;

import com.fabada.agendamento.execption.PersonExistException;
import com.fabada.agendamento.execption.UserRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonError {

    @ExceptionHandler(value = PersonExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handlePersonExistException(PersonExistException e){
        return new MessageErro(e.getMessage());
    }
}
