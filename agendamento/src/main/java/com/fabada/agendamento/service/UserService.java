package com.fabada.agendamento.service;

import com.fabada.agendamento.dto.UpdatePasswordDTO;
import com.fabada.agendamento.dto.UpdateRoleDTO;
import com.fabada.agendamento.enums.UserRole;
import com.fabada.agendamento.execption.CodeNotFoundException;
import com.fabada.agendamento.execption.CodeOrUsernameInvalid;
import com.fabada.agendamento.execption.UsernameNotFoundException;
import com.fabada.agendamento.model.CodeManager;
import com.fabada.agendamento.model.User;
import com.fabada.agendamento.repository.CodeRepository;
import com.fabada.agendamento.repository.UserRepository;
import com.fabada.agendamento.utils.PasswordEncoderInterface;
import com.fabada.agendamento.validated.UserRoleValidated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    private final CodeRepository codeRepository;
    private final PasswordEncoderInterface passwordEncoder;

    public UserService(UserRepository userRepository, CodeRepository codeRepository, PasswordEncoderInterface passwordEncoder) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserbyUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("username not found");
        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordDTO passwordDTO) {
         Optional<CodeManager> codeManager = codeRepository.findByCode(passwordDTO.code());

         if(codeManager.isEmpty()) throw new CodeNotFoundException("code not found");
         if(!(codeManager.get().getCode() == passwordDTO.code()) && !(codeManager.get().getUserId().getUsername().equals(passwordDTO.username()))) throw new CodeOrUsernameInvalid("code or username invalid");

         User user = codeManager.get().getUserId();
         user.setPassword(passwordEncoder.encoder(passwordDTO.password()));
         userRepository.save(user);
    }

    @Override
    public void updateRole(UpdateRoleDTO updateRoleDTO) {
      User user = this.getUserbyUsername(updateRoleDTO.username());
      UserRoleValidated userRoleValidated = new UserRoleValidated(updateRoleDTO.role());
      userRoleValidated.verify();

      user.setRole(UserRole.valueOf(updateRoleDTO.role()));
      userRepository.save(user);
    }
}
