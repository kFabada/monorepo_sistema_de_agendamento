package com.fabada.agendamento.service;

import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import com.fabada.agendamento.utils.RandomCode;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service

public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;
    private final UserService userService;
    private final RandomCode randomCode;

    public CodeServiceImpl(CodeRepository codeRepository, @Lazy UserService userService, RandomCode randomCode) {
        this.codeRepository = codeRepository;
        this.userService = userService;
        this.randomCode = randomCode;
    }

    @Override
    public void generateCode(String email) {
       User user = userService.findByEmail(email);
       Optional<CodeManager> optionalCode = codeRepository.findByUserId(user);

       String code = randomCode.createCode(6, 9);
       CodeManager codeManager = buildCodeManager(user, optionalCode, code);

       codeRepository.save(codeManager);
    }

    private CodeManager buildCodeManager(User user, Optional<CodeManager> optionalCode, String code){
        LocalDateTime registerTime = LocalDateTime.now();

        CodeManager codeManager = CodeManager
                .builder()
                .user(user)
                .register(registerTime)
                .timeValid(registerTime.plusMinutes(60))
                .code(Integer.parseInt(code)).build();

       optionalCode.ifPresent(manager -> codeManager.setId(manager.getId()));
       optionalCode.ifPresent(manager -> codeManager.setUsed(false));

       return codeManager;
    }



    @Override
    public Optional<CodeManager> findByUserId(User user) {
        return codeRepository.findByUserId(user);
    }

    @Override
    public Optional<CodeManager> findByCode(int code) {
        return codeRepository.findByCode(code);
    }

    @Override
    public void save(CodeManager codeManager) {
        codeRepository.save(codeManager);
    }
}
