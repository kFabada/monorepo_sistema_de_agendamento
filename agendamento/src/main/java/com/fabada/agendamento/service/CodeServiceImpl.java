package com.fabada.agendamento.service;

import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;
    private final UserService userService;

    public CodeServiceImpl(CodeRepository codeRepository, UserService userService) {
        this.codeRepository = codeRepository;
        this.userService = userService;
    }

    @Override
    public void gererateCode(String email) {
       User user = userService.findByEmail(email);
       Optional<CodeManager> optionalCode = codeRepository.findByUserId(user);
       CodeManager codeManager = new CodeManager();

       LocalDateTime localDateTime = LocalDateTime.now();
        String codeNumber = "";
        Random random = new Random();

       for(int i = 0; i <= 5; i++) codeNumber = codeNumber.concat(String.valueOf(random.nextInt(9)));


       optionalCode.ifPresent(manager -> codeManager.setId(manager.getId()));
       codeManager.setRegister(localDateTime);
       codeManager.setTimeValid(localDateTime.plusMinutes(5));
       codeManager.setCode(Integer.parseInt(codeNumber));
       codeManager.setUserId(user);
       codeRepository.save(codeManager);
    }
}
