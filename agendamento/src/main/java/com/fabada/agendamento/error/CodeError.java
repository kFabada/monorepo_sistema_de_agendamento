package com.fabada.agendamento.error;

import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class CodeError {

    @ExceptionHandler(value = CodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageErro handleCodeNotFoundException(CodeNotFoundException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value = CodeOrUsernameInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleRoleException(CodeOrUsernameInvalid e){
        return new MessageErro(e.getMessage());
    }


}
