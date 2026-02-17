package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;

import java.util.Optional;

public interface UserUpdatePasswordValidated {
    User verify(Optional<CodeManager> codeManager, UpdatePasswordDTO passwordDTO);
}
