package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.execption.CodeExpiredException;
import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserUpdatePasswordValidated implements UserUpdatePasswordValidatedInterface {

    @Override
    public User verify(Optional<CodeManager> codeManager, UpdatePasswordDTO passwordDTO){
        if(codeManager.isEmpty()) throw new CodeNotFoundException("code not found");
        if(!(codeManager.get().getCode() == Integer.parseInt(passwordDTO.code())) || !(codeManager.get().getUserId().getEmail().equals(passwordDTO.email())))
            throw new CodeOrUsernameInvalid("code or username invalid");
        if(LocalDateTime.now().isAfter(codeManager.get().getTimeValid())) throw new CodeExpiredException("code expired");
        return codeManager.get().getUserId();
    }
}
