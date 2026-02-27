package com.fabada.agendamento.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ErrorParamentersValidated {

    @Getter
    private class Error {
        private Map<String, String> erro;

        public Error(Map<String, String> erro) {
            this.erro = erro;
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> erroParameters = new HashMap<>();

        e.getFieldErrors().forEach(
                (field) -> erroParameters.put(field.getField(), field.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(erroParameters));
    }
}
