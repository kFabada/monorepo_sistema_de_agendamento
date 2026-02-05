package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.UserResponseDTO;
import com.fabada.agendamento.service.UserServiceInterface;
import com.fabada.agendamento.utils.PasswordEncoderInterface;
import com.fabada.agendamento.validated.UserValidatedRegister;
import jakarta.validation.Valid;
import com.fabada.agendamento.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fabada.agendamento.dto.UserRegisterDTOValidated;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceInterface userService;
    private final UserValidatedRegister userValidatedRegister;
    private final PasswordEncoderInterface passwordEncoder;

    public UserController(UserServiceInterface userService, UserValidatedRegister userValidatedRegister, PasswordEncoderInterface passwordEncoder) {
        this.userService = userService;
        this.userValidatedRegister = userValidatedRegister;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @RequestMapping("/registers")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTOValidated userDTOValidated){
       User userMap = userDTOValidated.mapToUser();

       userValidatedRegister.userVerify(userMap);
       userMap.setPassword(passwordEncoder.encoder(userMap.getPassword()));

       User user = userService.save(userMap);
       return ResponseEntity.ok(
               new UserResponseDTO(
                   user.getId(),
                   user.getUsername(),
                   user.getRole())
       );
    }
}
