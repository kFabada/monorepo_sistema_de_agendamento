package com.fabada.agendamento.error;

import com.fabada.agendamento.execption.CodeExpiredException;
import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import com.fabada.agendamento.execption.CodeUsedException;
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

    @ExceptionHandler(value = CodeExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleCodeExpiredException(CodeExpiredException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value = CodeUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleCodeUsedException(CodeUsedException e){
        return new MessageErro(e.getMessage());
    }

}
