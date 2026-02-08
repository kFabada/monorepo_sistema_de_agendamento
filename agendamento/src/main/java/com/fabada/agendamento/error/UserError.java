package com.fabada.agendamento.error;

import com.fabada.agendamento.execption.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class UserError {

    @ExceptionHandler(value = UserRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleRoleException(UserRoleException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageErro handleUsernameNotFoundException(UsernameNotFoundException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value =  UsernameExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleUsernameExistException(UsernameExistException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value =  EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MessageErro handleEmailNotFoundException(EmailNotFoundException e){
        return new MessageErro(e.getMessage());
    }

    @ExceptionHandler(value =  EmailExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageErro handleEmailExistException(EmailExistException e){
        return new MessageErro(e.getMessage());
    }


}
