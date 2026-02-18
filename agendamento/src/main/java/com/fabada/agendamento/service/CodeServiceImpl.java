package com.fabada.agendamento.service;

import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import com.fabada.agendamento.utils.RandomCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;
    private final UserService userService;
    private final RandomCode randomCode;

    public CodeServiceImpl(CodeRepository codeRepository, UserService userService, RandomCode randomCode) {
        this.codeRepository = codeRepository;
        this.userService = userService;
        this.randomCode = randomCode;
    }

    @Override
    public void gererateCode(String email) {
       User user = userService.findByEmail(email);
       Optional<CodeManager> optionalCode = codeRepository.findByUserId(user);
       CodeManager codeManager = new CodeManager();

       LocalDateTime registerTime = LocalDateTime.now();
       String code = randomCode.createCode(6, 9);

       optionalCode.ifPresent(manager -> codeManager.setId(manager.getId()));
       codeManager.setRegister(registerTime);
       codeManager.setTimeValid(registerTime.plusMinutes(5));
       codeManager.setCode(Integer.parseInt(code));
       codeManager.setUserId(user);
       codeRepository.save(codeManager);
    }
}
