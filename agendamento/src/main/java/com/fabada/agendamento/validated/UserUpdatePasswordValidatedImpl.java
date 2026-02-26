package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.execption.CodeExpiredException;
import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import com.fabada.agendamento.execption.CodeUsedException;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserUpdatePasswordValidatedImpl implements UserUpdatePasswordValidated {

    @Override
    public User verify(CodeManager codeManager, UpdatePasswordDTO passwordDTO){
        if(codeManager.isUsed()) throw new CodeUsedException("code already used");

        if(!(codeManager.getCode() == Integer.parseInt(passwordDTO.code())) || !(codeManager.getUser().getEmail().equals(passwordDTO.email()))){
            throw new CodeOrUsernameInvalid("code or username invalid");
        }

        if(LocalDateTime.now().isAfter(codeManager.getTimeValid())) throw new CodeExpiredException("code expired");
        return codeManager.getUser();
    }
}
