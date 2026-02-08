package com.fabada.agendamento.controller;

import com.fabada.agendamento.dto.*;
import com.fabada.agendamento.service.UserServiceInterface;
import com.fabada.agendamento.utils.PasswordEncoderInterface;
import com.fabada.agendamento.validated.UserValidatedRegister;
import jakarta.validation.Valid;
import com.fabada.agendamento.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceInterface userService;
    private final UserValidatedRegister userValidatedRegister;
    private final PasswordEncoderInterface passwordEncoder;

    public UserController(UserServiceInterface userService, UserValidatedRegister userValidatedRegister, PasswordEncoderInterface passwordEncoder) {
        this.userService = userService;
        this.userValidatedRegister = userValidatedRegister;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userDTOValidated){
       User userMap = userDTOValidated.mapToUser();

       userValidatedRegister.verify(userMap);
       userMap.setPassword(passwordEncoder.encoder(userMap.getPassword()));

       User user = userService.save(userMap);
       return ResponseEntity.ok(
               new UserResponseDTO(
                   user.getId(),
                   user.getUsername(),
                   user.getRole())
       );
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

    @GetMapping("/get_users")
    public ResponseEntity<?> getAllPage(){

      return ResponseEntity.ok(userService.getAllPage(PageRequest.of(0, 15)));
    }
}
