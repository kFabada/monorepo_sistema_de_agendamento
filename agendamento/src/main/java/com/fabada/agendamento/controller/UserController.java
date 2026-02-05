package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.*;
import com.fabada.agendamento.service.CodeServiceInterface;
import com.fabada.agendamento.service.UserServiceInterface;
import com.fabada.agendamento.utils.PasswordEncoderInterface;
import com.fabada.agendamento.validated.UserValidatedRegister;
import jakarta.validation.Valid;
import com.fabada.agendamento.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceInterface userService;
    private final UserValidatedRegister userValidatedRegister;
    private final PasswordEncoderInterface passwordEncoder;
    private final CodeServiceInterface codeService;

    public UserController(UserServiceInterface userService, UserValidatedRegister userValidatedRegister, PasswordEncoderInterface passwordEncoder, CodeServiceInterface codeService) {
        this.userService = userService;
        this.userValidatedRegister = userValidatedRegister;
        this.passwordEncoder = passwordEncoder;
        this.codeService = codeService;
    }

    @PostMapping("/registers")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userDTOValidated){
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

    @PostMapping("/code_generete")
    public ResponseEntity<?> createCode(@Valid @RequestBody CreateCodeDTO createCodeDTO){
        codeService.gererateCode(createCodeDTO.username());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password_update")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO){
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/role_update")
    public ResponseEntity<?> updateRole(@Valid @RequestBody UpdateRoleDTO updateRoleDTO){
        userService.updateRole(updateRoleDTO);
        return ResponseEntity.ok().build();
    }
}
