package com.fabada.agendamento.service;

import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;

import java.util.Optional;

public interface CodeService {
    void generateCode(String username);
    Optional<CodeManager> findByUserId(User user);
    Optional<CodeManager> findByCode(int code);
    void save(CodeManager codeManager);
}
