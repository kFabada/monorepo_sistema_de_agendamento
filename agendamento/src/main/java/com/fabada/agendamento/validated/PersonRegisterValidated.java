package com.fabada.agendamento.validated;

import com.fabada.agendamento.dto.PersonRegisterDTO;
import com.fabada.agendamento.model.User;

public interface PersonRegisterValidated {
    User verify(PersonRegisterDTO personRegisterDTO);
}
