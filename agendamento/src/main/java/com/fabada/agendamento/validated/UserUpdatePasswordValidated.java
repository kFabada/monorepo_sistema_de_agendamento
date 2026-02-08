package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUpdatePasswordValidated implements UserUpdatePasswordValidatedInterface {

    @Override
    public User verify(Optional<CodeManager> codeManager, UpdatePasswordDTO passwordDTO){
        if(codeManager.isEmpty()) throw new CodeNotFoundException("code not found");
        if(!(codeManager.get().getCode() == passwordDTO.code()) || !(codeManager.get().getUserId().getUsername().equals(passwordDTO.username())))
            throw new CodeOrUsernameInvalid("code or username invalid");
        return codeManager.get().getUserId();
    }
}
