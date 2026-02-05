package com.fabada.agendamento.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CodeService implements CodeServiceInterface{
    private final CodeRepository codeRepository;
    private final UserServiceInterface userService;

    public CodeService(CodeRepository codeRepository, UserServiceInterface userService) {
        this.codeRepository = codeRepository;
        this.userService = userService;
    }

    @Override
    public void gererateCode(String username) {
       User user = userService.userbyUsername(username);
       Optional<CodeManager> optionalCode = codeRepository.findByUserId(user);
       CodeManager codeManager = new CodeManager();

       LocalDateTime localDateTime = LocalDateTime.now();
        String codeNumber = "";
        Random random = new Random();

       for(int i = 0; i <= 5; i++){
         codeNumber = codeNumber.concat(String.valueOf(random.nextInt(9)));
       }


       if(optionalCode.isPresent()) codeManager.setId(codeManager.getId());
       codeManager.setRegister(localDateTime);
       codeManager.setTimeValid(localDateTime.minusMinutes(30));
       codeManager.setCode(Integer.parseInt(codeNumber));
       codeManager.setUserId(user);
       codeRepository.save(codeManager);
    }
}
